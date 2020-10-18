package del.ac.id.demo.controller;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import del.ac.id.demo.jpa.Item;
import del.ac.id.demo.jpa.ItemRepository;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.mongodb.core.MongoTemplate;
import com.mongodb.client.result.UpdateResult;
import java.util.Optional;

@RestController
public class ItemController {
	@Autowired ItemRepository itemRepository;
	@Autowired MongoTemplate mongoTemplate;
	@RequestMapping("/item")
	public ModelAndView item() {
		List<Item> items = itemRepository.findAll();
		
		ModelAndView mv = new ModelAndView("item");
		mv.addObject("items", items);
		return mv;
	}
	 @GetMapping("/showFormForBuy")
	    public ModelAndView showFormForBuy(@RequestParam(value = "id") String id, @RequestParam(name="stock") double stock, @RequestParam(name="rating") double rating) {
	        Optional<Item> item = itemRepository.findById(id);
	        Query query = new Query(Criteria.where("id").is(id));
	        List<Item> item2 = mongoTemplate.find(query, Item.class);
	        if(item2!=null) {
	        	Update update = new Update();
	        	update.inc("stock", -stock);
	        	update.addToSet("sold", item.get().getSold()+stock);
	        	update.addToSet("rating", item.get().getRating()+rating/2);
	        	UpdateResult result = mongoTemplate.updateFirst(query, update, Item.class);
	        }
	        ModelAndView mv = new ModelAndView("redirect:/");
	        return mv;
	    }
	 @GetMapping("/item/formBuy/{id}")
	 public ModelAndView formBuy(@PathVariable (value="id") String id) {
		Optional<Item> item = itemRepository.findById(id);
		Query query = new Query(Criteria.where("id").is(id));
		List<Item> item2 = mongoTemplate.find(query, Item.class);
		if(item2 != null) {
			Update update = new Update().inc("seen", 1);
			UpdateResult result = mongoTemplate.updateFirst(query, update, Item.class);
			}
		item.get().setSeen(item.get().getSeen()+1);
		ModelAndView mv = new ModelAndView("show");
		mv.addObject("item", item);
		return mv;
	 }
}
