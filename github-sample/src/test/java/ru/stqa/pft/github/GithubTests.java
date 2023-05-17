package ru.stqa.pft.github;

import com.google.common.collect.ImmutableBiMap;
import com.jcabi.github.*;
import org.testng.annotations.Test;

import java.io.IOException;

public class GithubTests {

	@Test
	public void testCommits() throws IOException {
		Github github = new RtGithub("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");
		RepoCommits commits = github.repos().get(new Coordinates.Simple("VEKul31", "java")).commits();
		for (RepoCommit repoCommit: commits.iterate(new ImmutableBiMap.Builder<String, String>().build())){
			System.out.println(new RepoCommit.Smart(repoCommit).message());
		}
	}
}
