/**
 * 
 */
package com.ll.fruits;

/**
 * @author user
 * 
 */
public class Fruit {

	private int imgResId;
	private String name;
	private String voicePath;

	/**
	 * @param imgResId
	 *            the resource id of image
	 * @param name
	 *            name of the fruit
	 * @param voicePath
	 *            path to voice recording
	 */
	public Fruit(int imgResId, String name, String voicePath) {
		this.imgResId = imgResId;
		this.name = name;
		this.voicePath = voicePath;
	}

	/**
	 * The default constructor
	 */
	public Fruit() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the imgResId
	 */
	public int getImgResId() {
		return imgResId;
	}

	/**
	 * @param imgResId
	 *            the imgResId to set
	 */
	public void setImgResId(int imgResId) {
		this.imgResId = imgResId;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the voicePath
	 */
	public String getVoicePath() {
		return voicePath;
	}

	/**
	 * @param voicePath
	 *            the voicePath to set
	 */
	public void setVoicePath(String voicePath) {
		this.voicePath = voicePath;
	}

}
