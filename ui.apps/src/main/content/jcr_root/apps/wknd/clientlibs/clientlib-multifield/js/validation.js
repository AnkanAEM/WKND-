// (function ($, $document) {
//     "use strict";
 
//     $.validator.register("foundation.validation.validator", {
//         selector: "coral-multifield",
//         validate: function(el) {
//             console.log(el + "The value of el");
 
//             var totalPanels = el["0"].items.getAll().length;
//             var min;
//             var max;
//             if ($(el).data("min-item")){
//                 min = $(el).data("min-item");
//                 if(totalPanels < min) {
//                     return "Minimum numbers of items required are: " + min;
//                 }
//             }
//             if ($(el).data("max-item")){
//                 max = $(el).data("max-item");
//                 if(totalPanels > max) {
//                     return "Maximum numbers of items allowed are: " + max;
//                 }
//             }
 
//         }});
// })($, $(document));

$(window).adaptTo("foundation-registry").register("foundation.validation.validator",{
    selector: "coral-multifield",
    validate: function(el) {
        console.log(el + "The value of el");
        var totalPanels = document.querySelector(".multifield-validation").querySelectorAll("coral-multifield-item").length;

        var min;
        var max;
        if ($(el).data("min-item")){
            min = $(el).data("min-item");
            if(totalPanels < min) {
                return "Minimum numbers of items required are: " + min;
            }
        }
        if ($(el).data("max-item")){
            max = $(el).data("max-item");
            if(totalPanels > max) {
                return "Maximum numbers of items allowed are: " + max;
            }
        }

    }});