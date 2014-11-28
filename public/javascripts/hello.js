if (window.console) {
  console.log("Welcome to your Play application's JavaScript!");
}

function mudaMeta(id, elemento) {
    var meta = "#"+id;
    $(meta).css("font-weight","bold");
    $(elemento).hide();
}

