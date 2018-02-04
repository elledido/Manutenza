//colora le stelle in base alla valutazione
function setRate(rate) {
    //colora le stelle piene
    for (i = 1; i <= rate; i++) {
        $("#star" + i).addClass("fa-star");
        $("#star" + i).removeClass("fa-star-o");
    }
    //colora le stelle vuote
    for (i = rate + 1; i <= 5; i++) {
        $("#star" + i).addClass("fa-star-o");
        $("#star" + i).removeClass("fa-star");
    }
}


