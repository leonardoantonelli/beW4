package Mezzi;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name= "tram")
@DiscriminatorValue("Tram")
public class Tram extends MezzoDiTrasporto {
	
	public Tram() {
		super();
	}

}
