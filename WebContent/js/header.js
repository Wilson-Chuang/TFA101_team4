/* 範圍滑塊 */
$("#range-slider").wRunner({
  // step size
  step: 5,

  // or 'range'
  type: "single",

  // min/max values
  limits: {
    minLimit: 5, 
    maxLimit: 60
  },

  // default value
  singleValue: 5,
  
  // root element
  roots: document.body,

  // the number of divisions
  scaleDivisionsCount: 0,

  // shows labels
  valueNoteDisplay: true,

  // theme name
  theme: "default",

  // or 'vertical'
  direction: 'horizontal'
});

$("#range-slider").on("DOMSubtreeModified", ".wrunner__value-note" ,function() {
  const result = $(".wrunner__value-note > div").text().split("分鐘");
  $("#reachtime").html(result);
  $("#btn-reachtime").attr("value", result.slice(0, -1));
});

/* 交通工具標籤 */
$("#motorbike").on("click",function() {
  $("#route").html("機車");
});
$("#bike").on("click",function() {
  $("#route").html("腳踏車");
});
$("#walk").on("click",function() {
  $("#route").html("走路");
});
$("#car").on("click",function() {
  $("#route").html("汽車");
});
$("#train").on("click",function() {
  $("#route").html("大眾運輸");
});


/* 搜尋關鍵字提示 */
const DEFAULTS = {
  treshold: 1,
  maximumItems: 5,
  highlightTyped: true,
  highlightClass: 'text-primary',

};

class Autocomplete {
  constructor(field, options) {
    this.field = field;
    this.options = Object.assign({}, DEFAULTS, options);
    this.dropdown = null;

    field.parentNode.classList.add('dropdown');
    field.setAttribute('data-toggle', 'dropdown');
    field.classList.add('dropdown-toggle');

    const dropdown = ce(`<div class="dropdown-menu" ></div>`);
    if (this.options.dropdownClass)
      dropdown.classList.add(this.options.dropdownClass);

    insertAfter(dropdown, field);

    this.dropdown = new bootstrap.Dropdown(field, this.options.dropdownOptions)

    field.addEventListener('click', (e) => {
      if (this.createItems() === 0) {
        e.stopPropagation();
        this.dropdown.hide();
      }
    });

    field.addEventListener('input', () => {
      if (this.options.onInput)
        this.options.onInput(this.field.value);
      this.renderIfNeeded();
    });

    field.addEventListener('keydown', (e) => {
      if (e.keyCode === 27) {
        this.dropdown.hide();
        return;
      }
      if (e.keyCode === 40) {
        this.dropdown._menu.children[0].focus();
        return;
      }
    });
  }

  setData(data) {
    this.options.data = data;
    this.renderIfNeeded();
  }

  renderIfNeeded() {
    if (this.createItems() > 0)
      this.dropdown.show();
    else
      this.field.click();
  }

  createItem(lookup, item) {
    let label;
    if (this.options.highlightTyped) {
      const idx = item.label.toLowerCase().indexOf(lookup.toLowerCase());
      const className = Array.isArray(this.options.highlightClass) ? this.options.highlightClass.join(' ')
        : (typeof this.options.highlightClass == 'string' ? this.options.highlightClass : '');
      label = item.label.substring(0, idx)
        + `<span class="${className}">${item.label.substring(idx, idx + lookup.length)}</span>`
        + item.label.substring(idx + lookup.length, item.label.length);
    } else {
      label = item.label;
    }
    if (this.options.showValue) {
      label += ` ${item.value}`;
    }
    return ce(`<button type="button" class="dropdown-item" data-label="${item.label}" data-value="${item.value}">${label}</button>`);
  }

  createItems() {
    const lookup = this.field.value;
    if (lookup.length < this.options.treshold) {
      this.dropdown.hide();
      return 0;
    }

    const items = this.field.nextSibling;
    items.innerHTML = '';

    let count = 0;
    for (let i = 0; i < this.options.data.length; i++) {
      const {label, value} = this.options.data[i];
      const item = {label, value};
      if (item.label.toLowerCase().indexOf(lookup.toLowerCase()) >= 0) {
        items.appendChild(this.createItem(lookup, item));
        if (this.options.maximumItems > 0 && ++count >= this.options.maximumItems)
          break;
      }
    }

    this.field.nextSibling.querySelectorAll('.dropdown-item').forEach((item) => {
      item.addEventListener('click', (e) => {
        let dataValue = e.target.getAttribute('data-value');
        this.field.value = e.target.innerText;
        if (this.options.onSelectItem)
          this.options.onSelectItem({
            value: e.target.dataset.value,
            label: e.target.innerText,
          });
        this.dropdown.hide();
      })
    });

    return items.childNodes.length;
  }
}


function ce(html) {
  let div = document.createElement('div');
  div.innerHTML = html;
  return div.firstChild;
}

function insertAfter(elem, refElem) {
  return refElem.parentNode.insertBefore(elem, refElem.nextSibling)
}

var placesrc = [
    {
	    "label": "台北市"
	  },		  
	  {
	    "label": "基隆市"
	  },
	  {
	    "label": "新北市"
	  },
	  {
	    "label": "連江縣"
	  },
	  {
	    "label": "宜蘭縣"
	  },
	  {
	    "label": "釣魚臺"
	  },
	  {
	    "label": "新竹市"
	  },
	  {
	    "label": "新竹縣"
	  },
	  {
	    "label": "桃園市"
	  },
	  {
	    "label": "苗栗縣"
	  },
	  {
	    "label": "臺中市"
	  },
	  {
	    "label": "彰化縣"
	  },
	  {
	    "label": "南投縣"
	  },
	  {
	    "label": "嘉義市"
	  },
	  {
	    "label": "嘉義縣"
	  },
	  {
	    "label": "雲林縣"
	  },
	  {
	    "label": "臺南市"
	  },
	  {
	    "label": "高雄市"
	  },
	  {
	    "label": "南海島"
	  },
	  {
	    "label": "澎湖縣"
	  },
	  {
	    "label": "金門縣"
	  },
	  {
	    "label": "屏東縣"
	  },
	  {
	    "label": "臺東縣"
	  },
	  {
	    "label": "花蓮縣"
	  }
	];

var place = new Autocomplete(document.getElementById('place-bar'), {
	data: placesrc,
	onSelectItem: ({label, value}) => {
		console.log("搜尋選擇:", label, value);
	}
});

var keyword = new Autocomplete(document.getElementById('shop-keyword-bar'), {
	data: shopsrc,
	onSelectItem: ({label, value}) => {
		console.log("搜尋選擇:", label, value);
	}
});

$('#search-type').on('change', function () {
	let shop = 
	'<input type="text" name="place-bar" id="place-bar" class="form-control w-25 search-bar" placeholder="地點" />' + 
	'<input type="text" name="shop-keyword-bar" id="shop-keyword-bar" class="form-control w-25 search-bar" placeholder="關鍵字" />';
	
	let article =
	'<input type="text" name="article-keyword-bar" id="article-keyword-bar" class="form-control w-50 search-bar" placeholder="關鍵字" />';
	
	let product =
	'<input type="text" name="product-keyword-bar" id="product-keyword-bar" class="form-control w-50 search-bar" placeholder="關鍵字" />';
	
	let party =
	'<input type="text" name="party-keyword-bar" id="party-keyword-bar" class="form-control w-50 search-bar" placeholder="關鍵字" />';
		
	switch(this.value){
		case "shop":
			$(".search-bar").remove();
			$(shop).insertAfter("#search-type");
			place = new Autocomplete(document.getElementById('place-bar'), {
				data: placesrc,
				onSelectItem: ({label, value}) => {
					console.log("搜尋選擇:", label, value);
				}
			});
			keyword = new Autocomplete(document.getElementById('shop-keyword-bar'), {
				data: shopsrc,
				onSelectItem: ({label, value}) => {
					console.log("搜尋選擇:", label, value);
				}
			});
			if($(".dropdown-menu.show").length)
			{
				$(".dropdown-menu.show").remove();
			}			
			break;
		case "article":
			$(".search-bar").remove();
			$(article).insertAfter("#search-type");
			keyword = new Autocomplete(document.getElementById('article-keyword-bar'), {
				data: shopsrc,
				onSelectItem: ({label, value}) => {
					console.log("搜尋選擇:", label, value);
				}
			});
			if($(".dropdown-menu.show").length)
			{
				$(".dropdown-menu.show").remove();
			}
			break;
		case "product":
			$(".search-bar").remove();
			$(product).insertAfter("#search-type");
			keyword = new Autocomplete(document.getElementById('product-keyword-bar'), {
				data: shopsrc,
				onSelectItem: ({label, value}) => {
					console.log("搜尋選擇:", label, value);
				}
			});
			if($(".dropdown-menu.show").length)
			{
				$(".dropdown-menu.show").remove();
			}
			break;
		case "party":
			$(".search-bar").remove();
			$(party).insertAfter("#search-type");
			keyword = new Autocomplete(document.getElementById('party-keyword-bar'), {
				data: shopsrc,
				onSelectItem: ({label, value}) => {
					console.log("搜尋選擇:", label, value);
				}
			});
			if($(".dropdown-menu.show").length)
			{
				$(".dropdown-menu.show").remove();
			}
			break;
	}
});

$('#topnav').on('click', ".guest", function () {
	let logged = '<a href="#" class="link-dark text-decoration-none dropdown-toggle mx-4 logged" id="dropdownUser" data-bs-toggle="dropdown" aria-expanded="false">' +
		'<img src="https://github.com/mdo.png" alt="mdo" width="32" height="32" class="rounded-circle">' +
		'</a>' +
		'<ul class="dropdown-menu text-small mt-2 logged" aria-labelledby="dropdownUser">' +
		'<li><a class="dropdown-item" href="#">我的商家</a></li>' +
		'<li><a class="dropdown-item" href="#">設定</a></li>' +
		'<li><a class="dropdown-item" href="#">個人檔案</a></li>' +
		'<li><hr class="dropdown-divider"></li>' +
		'<li><a class="dropdown-item" href="#" id="logout">登出</a></li>' +
		'</ul>';
	$(".guest").remove();
	$(logged).insertAfter("#shop-join");
});

$('#topnav').on('click', "#logout", function () {
	let guest = '<button type="button" class="btn btn-outline-primary me-3 guest">登入</button>' +
		'<button type="button" class="btn btn-primary me-4 guest">註冊</button>';
	$(".logged").remove();
	$(guest).insertAfter("#shop-join");
});

document.addEventListener("DOMContentLoaded", function(){
	document.querySelectorAll('.navbar-expand-lg .nav-item').forEach(function(everyitem){
		everyitem.addEventListener('mouseover', function(e){
			let el_link = this.querySelector('a[data-bs-toggle]');
			if(el_link != null){
				let nextEl = el_link.nextElementSibling;
				el_link.classList.add('show');
				nextEl.classList.add('show');
			}
		});
		everyitem.addEventListener('mouseleave', function(e){
			let el_link = this.querySelector('a[data-bs-toggle]');

			if(el_link != null){
				let nextEl = el_link.nextElementSibling;
				el_link.classList.remove('show');
				nextEl.classList.remove('show');
			}
		})
	});
});