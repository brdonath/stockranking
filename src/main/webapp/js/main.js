$('.infinitescroll').jscroll({
    loadingHtml: '<img src="/img/loading.gif" alt="Loading" /> Loading...',
    autoTrigger: true,
    nextSelector: 'a.last',
    padding: 600,
    callback: function(){
        goColorbox();
    }
});

//colorbox
function goColorbox() {
    $(".inline").colorbox(
        {
            onOpen: function () {
                if ($('#mobilehidden').is(':hidden')) {
                    jQuery.colorbox.resize({width: '100%', height: '90%'});
                }
            },
            onComplete: function () {
                if ($('#mobilehidden').is(':hidden')) {
                    jQuery.colorbox.resize({width: '100%', height: '90%'});
                }
            },
            inline: true,
            width: "70%",
            rel: 'group1',
            scrolling: true,
            current: ""
        });
}

function refreshPage() {
    goColorbox();
}
refreshPage();

$("#e21").select2({
    multiple: true,
    query: function (query) {
        var data = {results: []};
        $.each(preload_data, function () {
            if (query.term.length == 0 || this.text.toUpperCase().indexOf(query.term.toUpperCase()) >= 0) {
                data.results.push({id: this.id, text: this.text });
            }
        });

        query.callback(data);
    }, formatResult: function (result) {
        return "<img class='company-flag' src='/img/" + result.id + ".gif' />  " + result.text;
    }, escapeMarkup: function (m) {
        return m;
    }
}).on("change", function (e) {
        var select = $("#e21").val();
        var url;
        if (select) {
            url = "/company/" + select;
            $(".select2-elements").show();
            $(".table-data-companies").hide();

            $.ajax(url).done(function (html) {
                $("a.last").remove();
                $(".select2-elements").empty().append(html);
                refreshPage();
            });
        } else {
            $(".select2-elements").hide();
            $(".table-data-companies").show();
        }
    });

//swipe
jQuery("#colorbox").swipe({
    //Generic swipe handler for all directions
    swipeLeft: function (event, direction, distance, duration, fingerCount) {
        jQuery.colorbox.next();

    },
    swipeRight: function (event, direction, distance, duration, fingerCount){
        jQuery.colorbox.prev();
    }
});

$(document).bind('cbox_open', function() {
    $('body').css({ overflow: 'hidden' });
}).bind('cbox_closed', function() {
    $('body').css({ overflow: '' });
});