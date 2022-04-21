package fr.univrouen.rss22.controllers;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.xml.sax.SAXException;

import fr.univrouen.rss22.model.Item;
import fr.univrouen.rss22.model.Items;
import fr.univrouen.rss22.repository.ItemRepo;

@Controller
public class GetControllers {

	@Autowired
	ItemRepo repo;
	static Items items = new Items();
	@GetMapping(value="/rss22/resume/html")	
    public String getListRSSin(Model model) {
	    model.addAttribute("items", repo.findAll());
        return "listItem";
	}
	
	@RequestMapping(value = "/rss22/resume/xml", produces = MediaType.APPLICATION_XML_VALUE)
	public @ResponseBody Items getListRSSxml(Model model) 
	  { 
	  	for (Item item : repo.findAll()) {
			items.getItems().add(item);
		}
	  	return items;
	  }
	@GetMapping("/rss22/html/{guid}")
	public String getItemHtml(@PathVariable("guid") long guid, Model model) {
	    Item item = repo.findById(guid)
	      .orElseThrow(() -> new IllegalArgumentException("Invalid item Id:" + guid));
	    
	    model.addAttribute("item", item);
	    return "itemInfo";
	}
	
	@RequestMapping(value = "/rss22/xml/{guid}", produces = MediaType.APPLICATION_XML_VALUE)
	public @ResponseBody Item getItemXML(@PathVariable("guid") long guid, Model model) {
		Item item = repo.findById(guid)
			      .orElseThrow(() -> new IllegalArgumentException("Invalid item Id:" + guid));
		model.addAttribute("item", item);
		return item;
	}
	@GetMapping("/rss22/delete/{guid}")
	public String deleteUser(@PathVariable("guid") long guid, Model model) {
	    Item item = repo.findById(guid)
	      .orElseThrow(() -> new IllegalArgumentException("Invalid item Id:" + guid));
	    repo.delete(item);
	    return "redirect:/rss22/resume/html";
	}
}
