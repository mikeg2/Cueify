package com.cueify.save;

import java.io.File;

import javax.xml.bind.JAXB;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

import com.cueify.cuelist.CueList;
import com.cueify.ticker.Tickable;
import com.cueify.ticker.TimeTicker;

@XmlRootElement(name = "project")
@XmlSeeAlso(com.cueify.output.AudioOutput.class)
public class Project {

	private CueList cueList;
	private TimeTicker ticker;

	@SuppressWarnings("unchecked")
	public Project() {
		ticker = new TimeTicker();
		ticker.startTicking();
	}
	
	public void save(File saveTo) {
		try {
			JAXBContext context = JAXBContext.newInstance(Project.class);
			Marshaller m = context.createMarshaller();
			m.marshal(this, saveTo);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}
	
	public static Project open(File openFile) throws JAXBException {
		JAXBContext jc = JAXBContext.newInstance(Project.class);
		Unmarshaller unmarshaller = jc.createUnmarshaller();
		Project p = (Project) unmarshaller.unmarshal(openFile);
		return p;
	}
	
	//Getters and Setters
	@XmlElement(name="cueListItem")
	@XmlElementWrapper(name="cueList")
	public CueList getCueList() {
		return cueList;
	}

	public void setCueList(CueList cueList) {
		this.cueList = cueList;
		ticker.getToTick().addIterableTickable((Iterable<? extends Tickable>) cueList.getCueList());;
	}
}
