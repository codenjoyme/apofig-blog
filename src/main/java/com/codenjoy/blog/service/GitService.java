package com.codenjoy.blog.service;

/*-
 * #%L
 * Codenjoy - it's a dojo-like platform from developers to developers.
 * %%
 * Copyright (C) 2012 - 2023 Codenjoy
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/gpl-3.0.html>.
 * #L%
 */

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.lib.StoredConfig;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

import static org.apache.commons.lang3.StringUtils.*;

@Slf4j
@Component
@RequiredArgsConstructor
public class GitService {

    private Optional<Git> clone(String repoURI, File directory) {
        try {
            log.info("Cloning '{}' into '{}'", repoURI, directory.getAbsolutePath());
            return Optional.ofNullable(Git.cloneRepository()
                    .setURI(repoURI)
                    .setDirectory(directory)
                    .call());
        } catch (GitAPIException e) {
            log.error("Can not clone repository: {}", repoURI, e);
            return Optional.empty();
        }
    }

    private Optional<Object> pull(File directory) {
        try {
            log.info("Pulling '{}'", directory.getAbsolutePath());

            Git git = Git.open(directory);

            StoredConfig config = git.getRepository().getConfig();
            config.setString("branch", "master", "remote", "origin");
            config.setString("branch", "master", "merge", "refs/heads/master");
            config.save();

            return Optional.ofNullable(Git.open(directory)
                    .pull()
                    .call());
        } catch (IOException | GitAPIException e) {
            log.error("Can not pull repository: {}", directory.getAbsolutePath(), e);
            return Optional.empty();
        }
    }

    public String directory(String repo) {
        if (repo.startsWith("file://")) {
            return substringAfter(repo, "file://");
        }
        return "data/" + substringAfterLast(substringBefore(repo, ".git"), "/");
    }

    public String pullOrClone(String repo) {
        String directory = directory(repo);
        File dir = new File(directory);
        if (dir.exists()) {
            pull(dir)
                    .orElseThrow(() -> new IllegalArgumentException(
                            "Can not pull repository: " + repo));
        } else {
            clone(repo, dir)
                    .orElseThrow(() -> new IllegalArgumentException(
                            "Can not clone repository: " + repo));
        }
        return directory;
    }
}