package com.globant;

import java.io.File;
import java.util.ArrayDeque;
import java.util.Deque;

public class RecentFileList {
	/**
	 * The limit of files in the list
	 */
	public static final int LIMIT = 15;
	/**
	 * The Collection
	 */
	private Deque<File> openFiles;

	/**
	 * The constructor initialize the collection
	 */
	public RecentFileList() {
		this.openFiles = new ArrayDeque<>(LIMIT);
	}

	/**
	 *
	 * @return true if the collection has no elements
	 */
	public Boolean isEmpty() {
		return this.openFiles.isEmpty();
	}

	/**
	 * - Move the file at the beginning if already exists on the list
	 * - Put the file at the beginning and deletes the oldest if the list is full
	 * - Add the file if nothing of what previously mentioned happens
	 * @param file
	 */
	public void add(File file) {
		if(this.openFiles.contains(file)){
			this.openFiles.remove(file);
			this.openFiles.addFirst(file);
		}else if(this.openFiles.size() == LIMIT){
			this.openFiles.pollLast();
			this.openFiles.addFirst(file);
		}else{
			this.openFiles.addFirst(file);
		}
	}

	/**
	 *
	 * @return the first element on the list
	 */
	public File getMostRecentOpenedFile() {
		return this.openFiles.getFirst();
	}

	/**
	 *
	 * @return the last element on the list
	 */
	public File getOlder() {
		return this.openFiles.getLast();
	}

	/**
	 *
	 * @return the quantity of elements on the list
	 */
	public int size() {
		return this.openFiles.size();
	}
}
