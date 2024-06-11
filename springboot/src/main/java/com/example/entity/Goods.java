package com.example.entity;

import javax.persistence.*;

/**
 * 游戏管理
 */
@Table(name = "goods")
public class Goods {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	/** 游戏图片 */
	@Column(name = "img")
	private String img;
	/** 游戏名称 */
	@Column(name = "name")
	private String name;
	/** 游戏分类 */
	@Transient
	private String type;
	/** 是否热门 */
	@Column(name = "ishot")
	private String ishot;
	/** 游戏价格 */
	@Column(name = "price")
	private Double price;
	/** 游戏原价 */
	@Column(name = "originalprice")
	private Double originalprice;

	/** 游戏介绍 */
	@Column(name = "description")
	private String description;
	/** 游戏分类Id */
	@Column(name = "typeId")
	private Integer typeId;

	@Column(name = "praise")
	private Integer praise;

	@Column(name="video")
	private String video;
	@Column(name="goodsLink")
	private String goodsLink;
	@Column(name="businessId")
	private String businessId;
	/** 发行日期 */
	@Column(name="time")
	private String time;
	@Column(name="gpu")
	private String gpu;

	public String getGpu() {
		return gpu;
	}

	public void setGpu(String gpu) {
		this.gpu = gpu;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}


	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getTypeId() {
		return typeId;
	}

	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}

	public Integer getPraise() {
		return praise;
	}

	public void setPraise(Integer praise) {
		this.praise = praise;
	}



	public String getIshot() {
		return ishot;
	}

	public void setIshot(String ishot) {
		this.ishot = ishot;
	}



	public Double getOriginalprice() {
		return originalprice;
	}

	public void setOriginalprice(Double originalprice) {
		this.originalprice = originalprice;
	}

	public String getVideo() {
		return video;
	}

	public void setVideo(String video) {
		this.video = video;
	}

	public String getGoodsLink() {
		return goodsLink;
	}

	public void setGoodsLink(String goodsLink) {
		this.goodsLink = goodsLink;
	}

	public String getBusinessId() {
		return businessId;
	}

	public void setBusinessId(String businessId) {
		this.businessId = businessId;
	}

}
