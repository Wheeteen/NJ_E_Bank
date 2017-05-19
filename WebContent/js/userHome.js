(function(){
	var vm = new Vue({
		el: 'body',
		data: {
			swipeinfo:[{
				"img":"../img/banner3.jpg",
				"url":"#",
			},{
				"img":"../img/banner4_3.jpg",
				"url":"#",
			},{
				"img":"../img/banner1.png",
				"url":"#",
			},{
				"img":"../img/banner5.jpg",
				"url":"#",
			},],
            mySwipe: {},
            slideNum: {},
            status: {
            	'noLogin': true,
            	'Login': false
            },
            userAccount:'',
		},
		ready:function(){
			this.getSwipe();
			this.getUser();
		},
		methods: {
			getUser: function(){
				this.$http.post('../sessionUser', {							
				}, {
					emulateJSON:true,
					headers:{
						'Content-Type':'application/json'	
					}
				}).then(function(res) {
					console.log(res.json());
					var json = res.json().success;
					if(json == 1){
						this.userAccount = res.json().msg;
						this.status.noLogin = false;
						this.status.Login = true;
					}else{
						this.status.noLogin = true;
						this.status.Login = false;
					}
					
				});
			},
			logout: function(){
				this.$http.post('../ULogOutServlet', {							
				}, {
					emulateJSON:true,
					headers:{
						'Content-Type':'application/json'	
					}
				}).then(function(res) {
					console.log(res.json());
					var json = res.json().success;
					if(json == 1){
						this.userAccount = '';
						this.status.noLogin = true;
						this.status.Login = false;
					}
					
				});
			},
			getSwipe: function(){
				console.log(this.swipeinfo);
	    		var self = this;
		        //获取子组件中分页小圈圈
		        var slides = self.$els.swipe.getElementsByClassName('swipe-pagination-switch');
		        self.mySwipe = new Swipe(self.$els.swipe, {
		            startSlide: 0,
		            continuous: true,
		            speed: 1000,
		            auto: 5000,
		            stopPropagation: false,
		            callback: function(index, elem) {
		                //渲染分页小圈圈
		                for (var i = 0; i < slides.length; i++) {
		                    if (i != index) {
		                        slides[i].style.opacity = "0.2";
		                        slides[i].style.background = "#000";
		                    } else {
		                        slides[index].style.opacity = "1";
		                        slides[index].style.background = "#a71e32";
		                    }
		                }
		            },
		        });
		        self.slideNum = self.mySwipe.getNumSlides() - 1;
	    	},
	    	//点击底部小圈圈，跳到其所对应页
	        slideToCur: function(index) {
	            var self = this;
	            self.mySwipe.slide(index, 300);
	        },
		}
	})
})();