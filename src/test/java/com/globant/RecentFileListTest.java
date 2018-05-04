package com.globant;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class RecentFileListTest {

	RecentFileList rfl;

	@Rule
	public TemporaryFolder folder = new TemporaryFolder();

	@Before
	public void setUp(){
		this.rfl = new RecentFileList();
	}

	@Test
	public void emptyListWhenStarts(){
		assertTrue(rfl.isEmpty());
	}

	@Test
	public void openFile() throws IOException {
		final File aFile = folder.newFile("file.txt");
		rfl.add(aFile);
		File f = rfl.getLastOpen();
		assertEquals(aFile,f);
	}

	@Test
	public void openAlreadyOpenedFile() throws IOException {
		final File aFile = folder.newFile("file.txt");
		rfl.add(aFile);
		final File aFileCopy = new File("file.txt");
		rfl.add(aFileCopy);
		assertEquals(aFile,rfl.getLastOpen());
	}

	@Test
	public void testFullList() throws IOException {
		for(int i = 0 ; i<20 ; i++){
			rfl.add(folder.newFile("file" + (i+1) + ".txt"));
		}
		assertEquals(15,rfl.size());
	}

	@Test
	public void testBehaviourWhenFullList() throws IOException {
		for(int i = 0 ; i<16 ; i++){
			rfl.add(folder.newFile("file" + (i+1) + ".txt"));
		}
		assertEquals(new File("file2.txt"),rfl.getOlder());
	}

}
