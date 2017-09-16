(function(b) {
    var d = Object.prototype.toString,
        f = Array.prototype.slice,
        a = true;
    try {
        if (typeof document !== "undefined") {
            f.call(document.getElementsByTagName("body"))
        }
    } catch (c) {
        a = false
    }
    b.isArray = ("isArray" in Array) ? Array.isArray : function(e) {
        return d.call(e) === "[object Array]"
    };
    b.toArray = function(h, k, e) {
        if (!h || !h.length) {
            return []
        }
        if (typeof h === "string") {
            h = h.split("")
        }
        if (a) {
            return f.call(h, k || 0, e || h.length)
        }
        var j = [],
            g;
        k = k || 0;
        e = e ? ((e < 0) ? h.length + e : e) : h.length;
        for (g = k; g < e; g++) {
            j.push(h[g])
        }
        return j
    };
    b.later = function(o, n, j, g, i) {
        n = n || 0;
        var h = o,
            l = b.toArray(i),
            k, e;
        if (typeof o == "object") {
            h = g[o]
        }
        if (!h) {
            return
        }
        k = function() {
            h.apply(g, l)
        };
        e = (j) ? setInterval(k, n) : setTimeout(k, n);
        return {
            id: e,
            interval: j,
            cancel: function() {
                if (this.interval) {
                    clearInterval(e)
                } else {
                    clearTimeout(e)
                }
            }
        }
    };
    b.buffer = function(h, e, g) {
        e = e || 150;
        if (e === -1) {
            return (function() {
                h.apply(g || this, arguments)
            })
        }
        var j = null;

        function i() {
            i.stop();
            j = b.later(h, e, false, g || this, arguments)
        }
        i.stop = function() {
            if (j) {
                j.cancel();
                j = 0
            }
        };
        return i
    }
})(this);
(function(h) {
    h.ui = h.ui || {};
    var j = ".",
        g = "switchable-",
        f = g + "trigger-internal",
        d = g + "panel-internal",
        c = "forward",
        e = "backward",
        b = [],
        a = -1,
        i = function(l) {
            var m = document.body.scrollTop,
                o = document.documentElement.offsetHeight,
                n = l.offset(),
                k = l.height();
            return n.top > m && n.top + k < m + o
        };
    h.fn.extend({
        slide: function(n) {
            var k = (typeof n == "string"),
                l = Array.prototype.slice.call(arguments, 1),
                m = this;
            if (k) {
                this.each(function() {
                    var o = b[h.data(this, "slide-instance-id")],
                        p = (o && h.isFunction(o[n])) ? o[n].apply(o, l) : o;
                    if (typeof p !== "undefined" && p !== true && p !== false) {
                        m = p;
                        return false
                    }
                })
            } else {
                this.each(function() {
                    var o = h.data(this, "slide-instance-id");
                    if (typeof o !== "undefined" && b[o]) {
                        b[o].destroy()
                    }
                    o = parseInt(b.push({}), 10) - 1;
                    h.data(this, "slide-instance-id", o);
                    b[o] = new h.ui.slide(o, this, n);
                    b[o].init()
                })
            }
            return m
        }
    });
    h.ui.slide = function(l, n, m) {
        this.get_index = function() {
            return l
        };
        this.settings = h.extend({}, h.ui.slide.defaults, m);
        this.el = h(n);
        var k = this.settings;
        this.paused = false;
        this.activeIndex = k.index > -1 ? k.index : 0;
        this.locked = false;
        this.view = null;
        g = k.prefix || g
    };
    h.ui.slide.prototype = {
        init: function() {
            var k = this.settings;
            this.effect = k.effect;
            this.direction = k.direction.toLowerCase();
            k.callback.before && k.callback.before.apply(this);
            this._parseMarkup();
            this._refresh();
            this._bind();
            if (k.pauseOnScroll) {
                this.__scrollDetect = buffer(h.proxy(function() {
                    this[i(this.el) ? "_start" : "_stop"]()
                }, this), 200);
                h(window).scroll(this.__scrollDetect)
            } !! k.auto && this._start()
        },
        getEffect: function() {
            var l = this.effect,
                m = ["x", "y"],
                k;
            if (~l.indexOf(",")) {
                k = l.split(",")
            }
            if (l == "random") {
                k = ["fold", "slice", "slide", "shutter", "grow"]
            }
            if (k && k.length) {
                l = k[Math.floor(Math.random() * (k.length))];
                this.direction = m[Math.floor(Math.random() * (m.length))]
            }
            return (l || "scroll").toLowerCase()
        },
        _refresh: function() {
            var k = this.panels,
                m = this.getEffect();
            if (m == "normal") {
                k.hide().eq(this.activeIndex).show()
            } else {
                var l = h.noop;
                switch (m) {
                    case "slice":
                    case "slide":
                    case "fold":
                    case "shutter":
                    case "grow":
                        l = this.render;
                        break;
                    default:
                        if (h.isFunction(this["_init_" + m])) {
                            l = this["_init_" + m]
                        }
                }
                k.show();
                l && l.call(this)
            }
        },
        reset: function(l, k, o, m) {
            var p = this.settings,
                s = p.steps,
                r = this.panels,
                q = this.length,
                u = l * s,
                t = (l + 1) * s;
            var n = r.slice(u, t);
            n.css("position", "");
            n.css(k, "");
            if (m) {
                this.content.css(k, l ? -o * (q - 1) : "")
            }
        },
        adjust: function(l, k, n) {
            var o = this.settings,
                r = o.steps,
                q = this.panels,
                p = this.length,
                t = l * r,
                s = (l + 1) * r;
            var m = q.slice(t, s);
            m.css("position", "relative");
            m.css(k, (l ? -1 : 1) * n * p);
            return l ? n : -n * p
        },
        toggleTrigger: function(m, k) {
            var l = this.settings.cur;
            if (m.length) {
                m.removeClass(l)
            }
            k.addClass(l)
        },
        _getFromToPanels: function() {
            var m = this.fromIndex,
                p, o, k = this.settings.steps,
                l = this.panels,
                n = this.activeIndex;
            if (m > -1) {
                p = l.slice(m * k, (m + 1) * k)
            }
            o = l.slice(n * k, (n + 1) * k);
            return {
                fromPanels: p,
                toPanels: o
            }
        },
        getActiveItem: function(m) {
            var m = m === null || this.activeIndex,
                k = this.panels.eq(m),
                l = k.is("img") ? k : k.find("img:first");
            return {
                src: l.attr("src"),
                title: l.attr("alt") || l.attr("title") || ""
            }
        },
        setActiveItem: function() {
            this.panels.hide();
            var l = this.activeIndex,
                k = this.panels.eq(l),
                n = k.is("a"),
                m = k.find("a img").length;
            if (n) {
                k.css({
                    position: "absolute",
                    "z-index": this.length + 1,
                    width: "100%",
                    height: "100%",
                    "background-color": "#FFFFFF",
                    opacity: 0
                }).show().find("img").css("visibility", "hidden")
            } else {
                if (m) {
                    k.css({
                        position: "absolute",
                        "z-index": this.length + 1
                    }).show().find("img").css("visibility", "hidden");
                    k.find("a").css({
                        position: "absolute",
                        width: "100%",
                        height: "100%",
                        "background-color": "#FFFFFF",
                        opacity: 0
                    })
                }
            }
        },
        create_slices: function(r) {
            var q = this.getActiveItem(),
                l = this.settings,
                r = r == null ? l.slices : r,
                p = this.activeImg,
                n = l.view.width ? l.view.width : p.width(),
                k = l.view.height ? l.view.height :p.height(),
                o = Math.round(n / r);
            for (var m = 0; m < r; m++) {
                h('<div class="' + g + 'slice"><img src="' + q.src + '" style="position:absolute;top:0;left:-' + ((o + (m * o)) - o) + "px;width:" + n + "px;height:" + k + 'px;" alt="" /></div>').css({
                    position: "absolute",
                    top: 0,
                    left: o * m,
                    "z-index": this.length,
                    width: m === r - 1 ? (n - (o * m)) : o,
                    height: k,
                    opacity: 0,
                    overflow: "hidden",
                    visibility: "hidden"
                }).insertBefore(this.content)
            }
        },
        create_shutters: function() {
            var m = this.getActiveItem(),
                o = this.settings,
                n = this.activeImg,
                k = o.view.width ? o.view.width : n.width(),
                r = o.view.height ? o.view.height : n.height(),
                q = Math.round(k / o.shutters.cols),
                p = Math.round(r / o.shutters.rows);
            for (var s = 0; s < o.shutters.rows; s++) {
                for (var l = 0; l < o.shutters.cols; l++) {
                    h('<div class="' + g + 'shutter"><img src="' + m.src + '" style="position:absolute;top:-' + p * s + "px;left:-" + q * l + "px;width:" + k + "px;height:" + r + 'px;" alt="" /></div>').css({
                        position: "absolute",
                        top: p * s,
                        left: q * l,
                        "z-index": this.length,
                        width: l === o.shutters.cols - 1 ? (k - q * l) : q,
                        height: r,
                        opacity: 0,
                        overflow: "hidden",
                        visibility: "hidden"
                    }).insertBefore(this.content)
                }
            }
        },
        render: function() {
            var m = this.getActiveItem(),
                s = this.settings,
                _w = s.view.width ? s.view.width : m.width(),
                _h = s.view.height ? s.view.height : m.height(),
                l = h('<div class="' + g + 'panel"><img src="' + m.src + '" width="' + _w + '" height="' + _h + '" alt="' + m.title + '" /></div>').css("position", "absolute");
            l.insertAfter(this.content);
            this.panel = l;
            this.activeImg = l.find("img");
            var k = this.content.parent();
            if (k.css("position") == "static") {
                k.css("position", "relative")
            }
            this.setActiveItem()
        },
        slice: function() {
            var m = j + g;
            this.setActiveItem();
            this.el.find(m + "slice, " + m + "shutter").remove();
            this.create_slices();
            var l = this,
                k = this.settings,
                n = this.direction,
                o = this.el.find(m + "slice"),
                p = 0;
            if (n === "y") {
                o._reverse()
            }
            o.css("visibility", "visible").each(function(q) {
                var s = h(this),
                    r = q === k.slices - 1 ?
                        function() {
                            var t = l.getActiveItem();
                            l.activeImg.attr("src", t.src);
                            l.locked = false
                        } : h.noop;
                later(function() {
                    s.animate({
                        opacity: 1
                    }, k.speed, k.easing, r)
                }, 100 + p);
                p += 50
            })
        },
        fold: function() {
            var m = j + g;
            this.setActiveItem();
            this.el.find(m + "slice, " + m + "shutter").remove();
            this.create_slices();
            var l = this,
                k = this.settings,
                n = this.direction,
                o = this.el.find(m + "slice"),
                p = 0;
            if (n === "y") {
                o._reverse()
            }
            o.css("visibility", "visible").each(function(q) {
                var t = h(this),
                    s = t.width(),
                    r = q === k.slices - 1 ?
                        function() {
                            var u = l.getActiveItem();
                            l.activeImg.attr("src", u.src);
                            l.locked = false
                        } : h.noop;
                t.css("width", 0);
                later(function() {
                    t.animate({
                        width: s,
                        opacity: 1
                    }, k.speed, k.easing, r)
                }, 100 + p);
                p += 50
            })
        },
        slide: function() {
            var n = j + g;
            this.setActiveItem();
            this.el.find(n + "slice, " + n + "shutter").remove();
            this.create_slices(1);
            var m = this,
                k = this.settings,
                o = this.el.find(n + "slice").eq(0),
                l = this.content.width();
            o.css("visibility", "visible").css({
                width: 0,
                opacity: 1,
                left: "",
                right: 0
            }).animate({
                    width: l
                }, k.speed, k.easing, function() {
                    o.css({
                        left: 0,
                        right: ""
                    });
                    var p = m.getActiveItem();
                    m.activeImg.attr("src", p.src);
                    m.locked = false
                })
        },
        shutter: function() {
            var p = j + g;
            this.setActiveItem();
            this.el.find(p + "slice, " + p + "shutter").remove();
            this.create_shutters();
            var o = this,
                k = this.settings,
                l = this.el.find(p + "shutter"),
                n = k.shutters.cols * k.shutters.rows,
                q = 0,
                m = function(s) {
                    for (var t, r, u = s.length; u; t = parseInt(Math.random() * u, 10), r = s[--u], s[u] = s[t], s[t] = r) {}
                    return s
                };
            l = m(l);
            l.css("visibility", "visible").each(function(s) {
                var r = h(this),
                    t = s === n - 1 ?
                        function() {
                            var u = o.getActiveItem();
                            o.activeImg.attr("src", u.src);
                            o.locked = false
                        } : h.noop;
                later(function() {
                    r.animate({
                        opacity: 1
                    }, k.speed, k.easing, t)
                }, 100 + q);
                q += 20
            })
        },
        grow: function() {
            var o = j + g;
            this.setActiveItem();
            this.el.find(o + "slice, " + o + "shutter").remove();
            this.create_shutters();
            var w = this,
                q = this.settings,
                u = this.direction,
                p = this.el.find(o + "shutter"),
                t = q.shutters.cols * q.shutters.rows,
                n = 0,
                l = 0,
                k = 0,
                r = 0,
                v = [];
            v[r] = [];
            if (u === "y") {
                p._reverse()
            }
            p.each(function() {
                v[r][k] = h(this);
                k++;
                if (k == q.shutters.cols) {
                    r++;
                    k = 0;
                    v[r] = []
                }
            });
            for (var s = 0; s < (q.shutters.cols * 2); s++) {
                var m = s;
                for (var x = 0; x < q.shutters.rows; x++) {
                    if (m >= 0 && m < q.shutters.cols) {
                        (function(y, I, A, C, F) {
                            var H = v[I][y],
                                G = H.width(),
                                D = H.height(),
                                B = {
                                    slow: 600,
                                    fast: 200,
                                    _default: 400
                                },
                                z = typeof q.speed == "number" ? q.speed : B[q.speed] || B[_default],
                                E = C === F - 1 ?
                                    function() {
                                        var J = w.getActiveItem();
                                        w.activeImg.attr("src", J.src);
                                        w.locked = false
                                    } : h.noop;
                            H.width(0).height(0);
                            later(function() {
                                H.css("visibility", "visible").animate({
                                    opacity: 1,
                                    width: G,
                                    height: D
                                }, z / 1.3, q.easing, E)
                            }, 100 + A)
                        })(m, x, l, n, t);
                        n++
                    }
                    m--
                }
                l += 100
            }
        },
        _init_scroll: function() {
            var k = this.settings,
                n = this.content,
                m = this.panels,
                l = k.steps,
                p = this.direction;
            n.css("position", "absolute");
            var o = n.parent();
            if (o.css("position") == "static") {
                o.css("position", "relative")
            }
            this.view = {
                width: k.view.width ? k.view.width : m.eq(0).width() * l,
                height: k.view.height ? k.view.height : m.eq(0).height() * l
            }
            if (p == "x") {
                m.css({"float":"left",'width': this.view.width,'height':this.view.height});
                n.width("9999px")
            }

        },
        scroll: function(x, u) {
            var y = this,
                o = this.settings,
                v = this.fromIndex,
                n = this.activeIndex,
                p = this.length,
                r = {},
                A = this.direction === "x",
                k = A ? "left" : "top",
                z = A ? "width" : "height",
                m = this.view[z],
                w = -m * n,
                q = this.panels,
                t = o.steps,
                l, s = u === e;
            this.locked = false;
            l = (s && v === 0 && n === p - 1) || (!s && v === p - 1 && n === 0);
            if (this.anim) {
                this.anim.stop();
                if (q.eq(v * t).css("position") == "relative") {
                    this.reset(v, k, m, 1)
                }
            }
            if (l) {
                w = this.adjust(n, k, m)
            }
            r[k] = w;
            if (v > -1) {
                this.anim = this.content.animate(r, o.speed, o.easing, h.proxy(function() {
                    if (l) {
                        this.reset(n, k, m, 1)
                    }
                    y.anim = undefined;
                    x && x()
                }, this))
            } else {
                this.content.css(r);
                x && x()
            }
        },
        _init_fade: function() {
            var q = this,
                l = this.settings,
                n = this.panels,
                m = l.steps,
                o = this.activeIndex * m,
                k = o + m - 1,
                p;
            n.each(function(r) {
                p = r >= o && r <= k;
                h(this).css({
                    position: "absolute",
                    opacity: p ? 1 : 0,
                    zIndex: p ? q.length : 1
                })
            })
        },
        fade: function(r) {
            var l = this._getFromToPanels(),
                p = l.fromPanels,
                o = l.toPanels;
            this.locked = false;
            if (p && p.length !== 1) {
                return
            }
            var m = this,
                k = this.settings,
                q = p && p.length ? p.get(0) : null,
                n = o.get(0);
            if (this.anim) {
                this.anim.stop();
                this.anim.fromPanels.css({
                    zIndex: 1,
                    opacity: 0
                });
                this.anim.toPanels.css("z-index", this.length)
            }
            o.css("opacity", 1);
            if (q) {
                this.anim = p.fadeTo(k.speed, 0, function() {
                    o.css("z-index", m.length);
                    p.css("z-index", 1);
                    r && r()
                });
                this.anim.toPanels = o;
                this.anim.fromPanels = p
            } else {
                o.css("z-index", this.length);
                r && r()
            }
        },
        normal: function(n) {
            var k = this._getFromToPanels(),
                m = k.fromPanels,
                l = k.toPanels;
            if (m && m.length) {
                m.hide()
            }
            l.show();
            this.locked = false;
            n && n()
        },
        updateBtnStatus: function() {
            var l = this.settings.disableBtnCls,
                n = this.prevBtn,
                m = this.nextBtn,
                k = this.activeIndex;
            n.removeClass(l);
            m.removeClass(l);
            if (k == 0) {
                n.addClass(l)
            }
            if (k == this.length - 1) {
                m.addClass(l)
            }
        },
        _complete: function() {
            var k = this.settings;
            if (!k.circular) {
                this.updateBtnStatus()
            }
        },
        _switchView: function(n, o) {
            var k = this.settings,
                m = this.getEffect(),
                l = h.noop;
            if (h.isFunction(this[m])) {
                l = this[m]
            }
            k.callback.onselect && k.callback.onselect.apply(this);
            l && l.call(this, h.proxy(function() {
                this._complete();
                o && o.call(this)
            }, this), n)
        },
        switchTo: function(l, o, p) {
            var k = this.settings,
                n = this.activeIndex,
                m = this.triggers;
            if (this.locked) {
                return
            }
            if (this._triggerIsValid(l)) {
                return
            }
            this.fromIndex = n;
            this.locked = true;
            if (k.hasTriggers) {
                this.toggleTrigger(m.eq(this.activeIndex), m.eq(l))
            }
            if (o === undefined) {
                o = l > n ? c : e
            }
            this.activeIndex = l;
            if (this.settings.caption) {
                this.setCaption()
            }
            this._switchView(o, h.proxy(function() {
                p && p.call(this)
            }, this))
        },
        _start: function() {
            if (this.timer) {
                this.timer.cancel();
                this.timer = undefined
            }
            this.paused = false;
            this.timer = later(h.proxy(function() {
                if (this.paused) {
                    return
                }
                this.next()
            }, this), this.settings.interval, true)
        },
        prev: function() {
            this.switchTo((this.activeIndex - 1 + this.length) % this.length, e)
        },
        next: function() {
            this.switchTo((this.activeIndex + 1) % this.length, c)
        },
        _stop: function() {
            if (this.timer) {
                this.timer.cancel();
                this.timer = undefined
            }
            this.paused = true
        },
        _bind: function() {
            var l = this,
                k = this.settings;
            if (this.settings.hasTriggers) {
                this.triggers.addClass(f);
                this.nav.delegate(j + f, "click", function(o) {
                    var n = h(this);
                    var m = n.index();
                    l._onFocusTrigger(m)
                });
                if (k.evtype == "mouse") {
                    this.nav.delegate(j + f, "mouseenter", function(o) {
                        var n = h(this);
                        var m = n.index();
                        l._onMouseEnterTrigger(m)
                    }).delegate(j + f, "mouseleave", function(m) {
                            l._onMouseLeaveTrigger()
                        })
                }
            }
            if (k.pauseOnHover) {
                this.el.hover(h.proxy(this._stop, this), h.proxy(function() { !! k.auto && this._start()
                }, this))
            }
            h.each(["prev", "next"], function() {
                var n = this,
                    m = l[n + "Btn"] = l.el.find(j + k[n + "BtnCls"]);
                m && m.length && m.mousedown(function(p) {
                    p.preventDefault();
                    var o = l.activeIndex;
                    if (n == "prev" && (o != 0 || k.circular)) {
                        l[n]()
                    }
                    if (n == "next" && (o != l.length - 1 || k.circular)) {
                        l[n]()
                    }
                })
            })
        },
        _triggerIsValid: function(k) {
            return this.activeIndex === k
        },
        _onFocusTrigger: function(k) {
            if (this._triggerIsValid(k)) {
                return
            }
            this._cancelSwitchTimer();
            this.switchTo(k)
        },
        _onMouseEnterTrigger: function(k) {
            if (this._triggerIsValid(k)) {
                return
            }
            this.switchTimer = later(h.proxy(function() {
                this.switchTo(k)
            }, this), this.settings.delay * 1000)
        },
        _onMouseLeaveTrigger: function(k) {
            this._cancelSwitchTimer()
        },
        _cancelSwitchTimer: function() {
            if (this.switchTimer) {
                this.switchTimer.cancel();
                this.switchTimer = undefined
            }
        },
        _parseMarkup: function() {
            var k = this.settings,
                q, p, o, l, r;
            switch (k.markupType) {
                case 0:
                    q = this.el.find(j + k.navCls);
                    if (q.length) {
                        o = q.children()
                    }
                    p = this.el.find(j + k.contentCls);
                    l = p.children();
                    break;
                case 1:
                    o = this.el.find(j + k.triggerCls);
                    l = this.el.find(j + k.panelCls);
                    break;
                case 2:
                    o = k.triggers;
                    l = k.panels;
                    break
            }
            r = l.length;
            this.length = Math.ceil(r / k.steps);
            this.nav = q && q.length ? q : (k.hasTriggers && o && o.length ? o.parent() : null);
            this.panels = l;
            this.content = p && p.length ? p : l.parent();
            if (k.hasTriggers && (!this.nav || !o || o.length == 0)) {
                o = this._generateTriggersMarkup(this.length)
            }
            this.triggers = o;
            if (k.caption) {
                var m = h('<div class="' + g + 'caption"></div>').css("z-index", this.length + 1).hide();
                m.insertAfter(this.content);
                this.caption = m;
                this.setCaption()
            }
        },
        setCaption: function() {
            var k = this.settings,
                o = this.getActiveItem(),
                p = o.title,
                m = this.caption,
                l = {
                    slow: 600,
                    fast: 200,
                    _default: 400
                },
                n = typeof k.speed == "number" ? k.speed : l[k.speed] || l[_default];
            if (p) {
                if (p.substr(0, 1) == "#") {
                    p = h(p).html()
                }
                if (m.is(":visible")) {
                    later(function() {
                        m.html(p)
                    }, n)
                } else {
                    m.html(p).stop().fadeIn(n)
                }
            } else {
                m.stop().fadeOut(n)
            }
        },
        _generateTriggersMarkup: function(k) {
            var l = this.settings,
                o = this.nav && this.nav.length ? this.nav : h('<div class="' + l.navCls + '"></div>'),
                n = [],
                m;
            for (m = 0; m < k; m++) {
                var p = this.getActiveItem(m);
                n.push("<li" + (m == this.activeIndex ? ' class="' + l.cur + '"' : "") + ">" + (l.controlThumbs ? '<img src="' + p.src + '" alt="' + p.title + '" />' : (m + 1)) + "</li>")
            }
            o.html('<ol class="' + l.navCls + '-ol">' + n.join("") + "</ol>");
            this.el.append(o);
            this.nav = o.find("ol");
            return o.find("li")
        },
        set_focus: function() {
            var k = h.ui.slide._focused();
            if (k !== this) {
                a = this.get_index()
            }
        },
        is_focused: function() {
            return a == this.get_index()
        },
        destroy: function() {
            var k, m = this.get_index(),
                l = this;
            if (this.is_focused()) {
                for (k in b) {
                    if (b.hasOwnProperty(k) && k != m) {
                        b[k].set_focus();
                        break
                    }
                }
            }
            if (m === a) {
                a = -1
            }
            b[m] = null;
            delete b[m]
        }
    };
    h.extend(h.ui.slide, {
        defaults: {
            auto: true,
            prefix: "switchable-",
            markupType: 0,
            navCls: g + "nav",
            contentCls: g + "content",
            triggerCls: "",
            panelCls: "",
            triggers: "",
            panels: "",
            hasTriggers: true,
            controlThumbs: false,
            caption: false,
            steps: 1,
            interval: 5000,
            cur: "active",
            index: -1,
            evtype: "click",
            delay: 0.1,
            view: {
                width: 0,
                height: 0
            },
            prevBtnCls: g + "prev-btn",
            nextBtnCls: g + "next-btn",
            disableBtnCls: g + "disable-btn",
            effect: "normal",
            direction: "x",
            speed: 1000,
            easing: "swing",
            circular: true,
            slices: 15,
            shutters: {
                cols: 8,
                rows: 4
            },
            pauseOnHover: true,
            pauseOnScroll: false,
            callback: {
                before: false,
                onselect: false
            }
        },
        _focused: function() {
            return b[a] || null
        }
    });
    h.fn._reverse = [].reverse
})(jQuery);