var boxes = [];

function openChat() {
    var email = this.id;

    var chatboxes = $("#chatboxes");
    var id = "chatbox" + email.replace("@", "");

    if ($.inArray(email, boxes, 0) === -1) {
        boxes.push(email);
        var divouter = $("<div class='col-md-4'></div>");
        var div = $("<div class='panel panel-default'></div>");
        var divheading = $("<div class='panel-heading top-bar'></div>");
        var title = $("<p></p>").text(email);
        var hideButton = $("<span class='glyphicon glyphicon-minus' aria-hidden='true'></span>");
        var closeButton = $("<span class='glyphicon glyphicon-remove' aria-hidden='true'></span>");
        var messages = $("<p></p>").text("messages");
        var divbody = $("<div class='panel-body'></div>");
        var inputgroup = $("<div class='input-group'></div>");
        var input = $("<input type='text' class='form-control'>");
        var span = $("<span class='input-group-btn'></span>");
        var button = $("<button class='btn btn-default' type='button'>Send</button>");

        inputgroup.append(input);
        inputgroup.append(span);
        span.append(button);


        chatboxes.append(divouter);
        divouter.append(div);
        div.append(divheading);
        divheading.append(title);
        title.append(hideButton);
        title.append(closeButton);

        divbody.append(messages);
        divbody.append(inputgroup);
        div.append(divbody);

        hideButton.on('click', function () {
            $(divbody).toggle();
        });

        closeButton.on('click', function () {
            $(divouter).remove();
            for (var i = 0; i < boxes.length; i++) {
                if (boxes[i] === email) {
                    delete boxes[i];
                }
            }
        });
    }
}