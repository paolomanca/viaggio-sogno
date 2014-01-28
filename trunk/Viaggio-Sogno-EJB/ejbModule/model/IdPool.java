package model;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

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
	
	public synchronized void free(int ID){
		pool.add(ID);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof IdPool))
			return false;
		IdPool other = (IdPool) obj;
		if (id != other.id)
			return false;
		return true;
	}

}
