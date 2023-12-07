# 2023-12-04
- Init commit. Created base that can load markdown file and show it in browser.
- Two screens - one list of pages with markdown file names is being worked out.  
The second is the actual page with content.

# 2023-12-05
- Added the ability to load a site from git - this happens on app startup. 
- If git repository is already cloned - it will be pulled.
- Fixed styles of list and content pages.
- UI. Rename 'List of Pages' to 'Table of content'.
- Added build script to push everything.
- Bug. Fixed time in pages list.
- Refactoring. Make all components like @RequiredArgsConstructor.
- Refactoring. Extracted git repo from constant to application.yml and .env

# 2023-12-06
- Refactoring. Move replace servlet context to service.
- Refactoring. Move logic from PagesFacade to GitService. 
- Refactoring. Rename PageDTO.path -> fileName.
- Created PageService and moved there all logic with work with pages. Added possibility to 
  parse page settings. The time for building the post title is taken from the settings. 
- Add sorting posts by time.

# 2023-12-07
- Reworked the file load method. 
- Added test.properties for tests. 
- Made it possible to work with local repositories for testing purposes. 
- Added UTF_8 encoding. 
- Fixed tests - made them work. 
- Updated Samples. 
- Content loading via rest api.
- It is now possible to retrieve pages by tags, you just need to specify 
  '[GET]/posts?tag=TAG_NAME'
- Added page with list of tags.
- Fixed tags design - now I put it inside []
- Split tags only by comma.