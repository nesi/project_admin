(function ($) {
	//begin the validator
	var validator = {
		regexObj : {
			"email" : /^[a-zA-Z0-9.!#$%&'*+\/=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)*$/,
			"chars" : /^.{1,100}$/
		},

		showhideErrorMsg : function(el, callback, msg) {
			if (!el) {
				return;
			}
			if (!msg) {
				msg = "";
			}
			callback.call(this, el, msg);
		},

		addError : function(el, msg) {
			if (!el.parent().hasClass("fx-error")) {
				el.parent().addClass("fx-error");
				el.after("<span class='errorMsg' style='padding-left: 5px; color: red;'>" + msg + "</span>");
			}
		},

		removeError : function(el, msg) {
			if (el.parent().hasClass("fx-error")) {
				el.siblings(".errorMsg").remove();
				el.parent().removeClass("fx-error");
			}
		},

		normalChecker : function(el, msg, regexStr) {
			var value = el.val();
			var isvalid;
			if (!regexStr) {
				if(el.is('input')){
					isvalid = $.trim(value).length > 0;
				}else{
					isvalid = parseInt(value) !== 0;
				}
			} else {
				isvalid = regexStr.test(value);
			}
			if (!isvalid) {
				this.showhideErrorMsg(el, function(el, msg) {
					this.addError(el, msg);
				}, msg);
			} else {
				this.showhideErrorMsg(el, function(el, msg) {
					this.removeError(el, msg);
				}, msg);
			}
			return isvalid;
		},

		emailChecker : function(el) {
			return this.normalChecker(el, "Please provide valid email address", this.regexObj["email"]);
		},
		
		charChecker : function(el) {
			return this.normalChecker(el, "Please give a name less than 100 characters", this.regexObj["chars"]);
		},

		requiredChecker : function(el) {
			return this.normalChecker(el, "Please enter required field");
		}
	};
	//end the validator

	//validate call the certain validator to do the validation
	var validate = (function() {
		return {
			doValidation : function(el, callback) {
				if ( typeof callback === "function") {
					return callback.call(validator, el);
				} else {
					var result = [];
					for (var i in callback) {
						if (callback.hasOwnProperty(i)) {
							if ( typeof callback[i] === "function")
								result.push(callback[i].call(validator, el));
						}
					}
					return result;
				}
			},

			doMultiValidation : function(els, applyone, callbacks) {
				var result = [];
				if (applyone) {
					if ( typeof callbacks !== "function") {
						callbacks = callbacks[0];
					}
					for (var i in els) {
						if (els.hasOwnProperty(i)) {
							result.push(this.doValidation(els[i], callbacks));
						}
					}
				} else {
					if (!$.isArray(els)) {
						els = [els];
					}
					for (var i in els) {
						if (els.hasOwnProperty(i)) {
							var el = els[i];
							result.push(this.doValidation(el, callbacks[el]));
						}
					}
				}
				return result;
			}
		};
	})();

	$.validate = validate;
	$.validator = validator;
})(jQuery);