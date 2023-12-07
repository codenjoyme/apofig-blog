$(document).ready(function() {
    loadPages();

    async function loadPages() {
        let secret = parameter('secret');
        let tag = parameter('tag');

        let pages = await getAllPages(tag);

        const $pagesDiv = $('#pages');
        $pagesDiv.html('');

        pages.forEach(page => {
            const $pageDiv = $('<div>').addClass('page');
            const $info = $('<div>').addClass('info');

            const $pageLink = pageLink(page);
            $info.append($pageLink);

            let $buttons = $('<div>');
            $info.append($buttons);

            // $buttons.append('(');
            // $buttons.append(actionLink('action1', page.path, secret));
            // $buttons.append(' | ');
            // $buttons.append(actionLink('action2', page.path, secret));
            // if (!!secret) {
            //     $buttons.append(' | ');
            //     $buttons.append(actionLink('action3', page.path, secret));
            //     $buttons.append(' | ');
            //     $buttons.append(linkClick('delete', async (event) => {
            //         event.preventDefault();
            //         try {
            //             await askDelete(`You\'re going to remove the page\n${page.description}\nAre you sure?`);
            //             await deletePage(page.path, secret);
            //             await loadPages();
            //         } catch (err) {
            //             // do nothing
            //         }
            //     }));
            // }
            // $buttons.append(')');
            $pageDiv.append($info);
            $pagesDiv.append($pageDiv);
        });
    }
});