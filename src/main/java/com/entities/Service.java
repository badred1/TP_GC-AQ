package com.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;

@Entity
public class Service {
	@Id
	@GeneratedValue
	private long Id;
	@Column(unique=true)
	private String name;
	private String serviceDescription;
	private String imgUrl;
	@OneToMany
	private List<Artisan> artisan;

	public Service() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Service(long id, String typeService, String serviceDescription, String imgUrl) {
		super(); 
		Id = id;
		this.name = typeService;
		this.serviceDescription = serviceDescription;
		this.imgUrl = imgUrl;
	}
/*
	@OneToMany
	@JoinTable(
	        uniqueConstraints=@UniqueConstraint(columnNames={"service_id","artisan_id"})
	    )
	private List<Artisan> artisan;*/
	

	public long getId() {
		return Id;
	}

	public void setId(long id) {
		Id = id;
	}

	public String getTypeService() {
		return name;
	}
	
	public String getImgUrl() {
		return imgUrl;
	}

	public void setTypeService(String typeService) {
		this.name = typeService;
	}

	public String getServiceDescription() {
		return serviceDescription;
	}

	public void setServiceDescription(String serviceDescription) {
		this.serviceDescription = serviceDescription;
	}
	
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	/*public Artisan getArtisan() {
		return artisan;
	}

	public void setArtisan(Artisan artisan) {
		this.artisan = artisan;
	}
	*/

}
