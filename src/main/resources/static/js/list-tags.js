$(document).ready(function() {
    loadTags();

    async function loadTags() {
        let tags = await getAllTags();

        const $tagsDiv = $('#tags');
        $tagsDiv.html('');

        tags.forEach(tag => {
            const $pageDiv = $('<div>').addClass('tag');
            const $info = $('<div>').addClass('info');

            $info.append(tagLink(tag));

            let $buttons = $('<div>');
            $info.append($buttons);

            $pageDiv.append($info);
            $tagsDiv.append($pageDiv);
        });
    }
});