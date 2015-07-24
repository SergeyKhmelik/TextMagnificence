function openUpdateModal(idStory, name, image, annotation, genre) {

    var header = "Изменить данные игры " + name;
    $('#myModalLabel').html(header);

    $('#idStoryUpdateInput').attr("value", idStory);

    $('#nameUpdateInput').attr("value", name);
    $('#nameUpdateInput').attr("placeholder", name);

    $('#imageUpdateInput').attr("value", image);
    $('#updatingImageLink').attr("href", image);
    $('#updatingImageLink').html(image);

    $('#annotationUpdateInput').html(annotation);

    $('#updateStoryModal').modal('show');
}

$('[data-dismiss=modal]').on('click', function (e) {
    var $t = $(this),
        target = $t[0].href || $t.data("target") || $t.parents('.modal') || [];

    $(target)
        .find("input,textarea,select")
        .val('')
        .end()
        .find("input[type=checkbox], input[type=radio]")
        .prop("checked", "")
        .end();
})