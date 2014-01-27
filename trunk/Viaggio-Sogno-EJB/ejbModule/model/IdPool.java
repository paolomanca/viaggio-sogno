package model;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="ID_POOL")
public class IdPool {

	private int next = 1;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="IDPOOL_ID", unique=true, nullable=false)
	private int id;
	
	@ElementCollection(targetClass = Integer.class)
	@Column(unique=true)
	private Set<Integer> pool = new HashSet<Integer>();
	
	@OneToOne(mappedBy="idPool")
	private User user;
	
	public synchronized int nextAvailable() {
		if(pool.isEmpty()){
			return next++;
		} else {
			Iterator<Integer> it = pool.iterator();
			int out = it.next();
			it.remove();
			return out;
		}
	}
	
	public void free(int ID){
		pool.add(ID);
	}

}
