package Mezzi;

import javax.persistence.*;


@Entity
@Table(name= "autobus")
@DiscriminatorValue("Autobus")
public class Autobus extends MezziDiTrasporto{
	
	
	
}
