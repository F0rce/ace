ace.define("ace/ext/statusbar",["require","exports","module","ace/lib/dom","ace/lib/lang"],function(e,t,n){"use strict";var r=e("../lib/dom"),i=e("../lib/lang"),s=function(e,t,n){n==undefined&&(n=1),this.element=r.createElement("div"),this.element.className="ace_status-indicator",this.element.style.cssText="display: inline-block;",t.appendChild(this.element);var s=i.delayedCall(function(){this.updateStatus(e,n)}.bind(this)).schedule.bind(null,100);e.on("changeStatus",s),e.on("changeSelection",s),e.on("keyboardActivity",s)};(function(){this.updateStatus=function(e,t){function r(e,t){e&&n.push(e,t||"|")}var n=[];r(e.keyBinding.getStatusText(e)),e.commands.recording&&r("REC");var i=e.selection,s=i.lead;if(!i.isEmpty()){var o=e.getSelectionRange();r("("+(parseInt(o.end.row+t)-parseInt(o.start.row+t))+":"+(parseInt(o.end.column+t)-parseInt(o.start.column+t))+")"," ")}r(parseInt(s.row+t)+":"+parseInt(s.column+t)," "),i.rangeCount&&r("["+i.rangeCount+"]"," "),n.pop(),this.element.textContent=n.join("")}}).call(s.prototype),t.StatusBar=s});
                (function() {
                    ace.require(["ace/ext/statusbar"], function(m) {
                        if (typeof module == "object" && typeof exports == "object" && module) {
                            module.exports = m;
                        }
                    });
                })();
            