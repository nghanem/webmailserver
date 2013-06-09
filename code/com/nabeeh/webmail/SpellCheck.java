package com.nabeeh.webmail;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.antlr.stringtemplate.StringTemplate;

import com.swabunga.spell.engine.SpellDictionary;
import com.swabunga.spell.engine.SpellDictionaryHashMap;
import com.swabunga.spell.event.SpellCheckEvent;
import com.swabunga.spell.event.SpellCheckListener;
import com.swabunga.spell.event.SpellChecker;
import com.swabunga.spell.event.StringWordTokenizer;


public class SpellCheck {
	/**
	* Webmail server Application for 601 class USF
	* @author Nabeeh Ghanem
	* @version 1.0
	*/

	static ArrayList<String> suggList;
	
	SpellDictionary dictionary;
	
	SpellChecker spellChecker;

	public SpellCheck() {
		try {
			dictionary = new SpellDictionaryHashMap(new File("eng_com.dic"));
			
			spellChecker = new SpellChecker(dictionary);
			
			spellChecker.addSpellCheckListener(new SuggestionListener());
			
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}
	
	public static void setSuggList(ArrayList<String> list) {
		suggList = list;
	}

	public static class SuggestionListener implements SpellCheckListener {
		public void spellingError(SpellCheckEvent event) {
			String incorrectWord = event.getInvalidWord();
			ArrayList list = new ArrayList();
			List suggestions = event.getSuggestions();
			list.add(incorrectWord);
			if (!suggestions.isEmpty()) {
				
				Iterator i = suggestions.iterator();
				
				while (i.hasNext()) {
					list.add(i.next());
				}
				SpellCheck.setSuggList(list);
			}
		}
	}

	public ArrayList<String> check(String word) throws FileNotFoundException,
			IOException {
		
		this.suggList = new ArrayList<String>();
		spellChecker.checkSpelling(new StringWordTokenizer(word));
		return this.suggList;
	}
}
