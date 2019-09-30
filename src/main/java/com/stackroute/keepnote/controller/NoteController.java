package com.stackroute.keepnote.controller;

import com.stackroute.keepnote.model.Note;
import com.stackroute.keepnote.repository.NoteRepository;
//import jdk.javadoc.internal.doclets.toolkit.util.DocFileIOException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDateTime;
import java.util.List;

/*Annotate the class with @Controller annotation. @Controller annotation is used to mark
 * any POJO class as a controller so that Spring can recognize this class as a Controller
 * */
@Controller
public class NoteController {
	/*
	 * From the problem statement, we can understand that the application
	 * requires us to implement the following functionalities.
	 * 
	 * 1. display the list of existing notes from the collection. Each note 
	 *    should contain Note Id, title, content, status and created date.
	 * 2. Add a new note which should contain the note id, title, content and status.
	 * 3. Delete an existing note.
	 * 4. Update an existing note.
	 */
	
	/* 
	 * Get the application context from resources/beans.xml file using ClassPathXmlApplicationContext() class.
	 * Retrieve the Note object from the context.
	 * Retrieve the NoteRepository object from the context.
	 */
	ApplicationContext context=new ClassPathXmlApplicationContext("beans.xml");
	NoteRepository noteRepository=context.getBean(NoteRepository.class);
	Note note=context.getBean(Note.class);

	public NoteController() {

	}

	/*Define a handler method to read the existing notes by calling the getAllNotes() method
	 * of the NoteRepository class and add it to the ModelMap which is an implementation of Map 
	 * for use when building model data for use with views. it should map to the default URL i.e. "/" */
	
	@RequestMapping("/")
	public ModelAndView getNotes(){

		ModelAndView modelAndView=new ModelAndView();

		List<Note> notes=noteRepository.getAllNotes();

		modelAndView.addObject("notes",notes);
		modelAndView.setViewName("index");

		return modelAndView;
	}
	/*Define a handler method which will read the Note data from request parameters and
	 * save the note by calling the addNote() method of NoteRepository class. Please note 
	 * that the createdAt field should always be auto populated with system time and should not be accepted 
	 * from the user. Also, after saving the note, it should show the same along with existing 
	 * notes. Hence, reading notes has to be done here again and the retrieved notes object 
	 * should be sent back to the view using ModelMap.
	 * This handler method should map to the URL "/saveNote". 
	*/

	@RequestMapping("/saveNote")
	public ModelAndView add(@RequestParam int noteId,@RequestParam String noteTitle,@RequestParam String noteStatus,@RequestParam String noteContent){
		ModelAndView modelAndView=new ModelAndView();
		//noteRepository=context.getBean(NoteRepository.class);
		note = context.getBean(Note.class);
		note.setNoteId(noteId);
		note.setNoteTitle(noteTitle);
		note.setNoteStatus(noteStatus);
		note.setNoteContent(noteContent);
		LocalDateTime localDateTime=LocalDateTime.now();
		note.setCreatedAt(localDateTime);

		System.out.println(noteRepository.hashCode());

		noteRepository.addNote(note);
		List<Note> notes=noteRepository.getAllNotes();

		System.out.println("Size of list " + notes.size() + " First element ");

		modelAndView.addObject("notes",notes);
		modelAndView.setViewName("index");

		return modelAndView;
	}
	
	/* Define a handler method to delete an existing note by calling the deleteNote() method
	 * of the NoteRepository class
	 * This handler method should map to the URL "/deleteNote" 
	*/
	@RequestMapping("/deleteNote")
	public ModelAndView delete(@RequestParam int noteId){

		ModelAndView modelAndView=new ModelAndView();

		System.out.println(noteRepository.hashCode());

		List<Note> notes1=noteRepository.getAllNotes();
		System.out.println("Size of list before deletion " + notes1.size());

		noteRepository.deleteNote(noteId);

		List<Note> notes=noteRepository.getAllNotes();
		System.out.println("Size of list after deletion" + notes.size() );

		modelAndView.addObject("notes",notes);
		modelAndView.setViewName("redirect:/");

		return modelAndView;
	}
}