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

	private RecentFileList rfl;
	/**
	 * This rule indicates JUnit to create temporary folders and files which will be deleted after running all the tests
	 */
	@Rule public TemporaryFolder folder = new TemporaryFolder();

	@Before public void setUp() {
		this.rfl = new RecentFileList();
	}

	@Test public void whenNoFileHasBeenOpenedThenIsEmptyShouldReturnTrue() {
		assertTrue(rfl.isEmpty());
	}

	@Test public void whenTheFirstFileIsOpenedThenItWillBeTheFirstFileInTheList() throws IOException {
		final File aFile = folder.newFile("file.txt");
		rfl.add(aFile);
		File f = rfl.getMostRecentOpenedFile();
		assertEquals(aFile, f);
	}

	@Test public void whenOpenAnAlreadyOpenedFileThenTheFileIsBumpedToTheTop() throws IOException {
		final File aFile = folder.newFile("file.txt");
		rfl.add(aFile);
		final File bFile = folder.newFile("bfile.txt");
		rfl.add(bFile);
		final File aFileCopy = new File(folder.getRoot() + "\\file.txt");
		rfl.add(aFileCopy);
		assertEquals(aFile, rfl.getMostRecentOpenedFile());
	}

	@Test public void whenTheListIsFullThenStartsRemovingTheOldestFiles() throws IOException {
		for (int i = 0; i < 16; i++) {
			rfl.add(folder.newFile("file" + (i + 1) + ".txt"));
		}
		assertEquals(new File(folder.getRoot() + "\\file2.txt"), rfl.getOlder());
		assertEquals(15, rfl.size());
	}

}
