/**
 * Implementing Drag and Drop functionality in AngularJS is easier than ever.
 * Demo: http://codef0rmer.github.com/angular-dragdrop/
 *
 * @version 1.0.3
 *
 * (c) 2013 Amit Gharat a.k.a codef0rmer <amit.2006.it@gmail.com> - amitgharat.wordpress.com
 */
var jqyoui = angular
  .module('ngDragDrop', [])
  .service('ngDragDropService', [
    '$timeout',
    '$parse',
    function ($timeout, $parse) {
      this.callEventCallback = function (scope, callbackName, event, ui) {
        if (!callbackName) {
          return;
        }
        var args = [event, ui];
        var match = callbackName.match(/^(.+)\((.+)\)$/);
        if (match !== null) {
          callbackName = match[1];
          values = eval('[' + match[0].replace(/^(.+)\(/, '').replace(/\)/, '') + ']');
          args.push.apply(args, values);
        }
        scope[callbackName].apply(scope, args);
      };
      this.invokeDrop = function (e, t, n, r) {
        var i = '',
          s = '',
          o = {},
          u = {},
          a = null,
          f = {},
          l = {},
          c,
          h,
          p = null,
          d = t.scope(),
          v = e.scope();
        i = e.attr('ng-model');
        s = t.attr('ng-model');
        c = v.$eval(i);
        h = d.$eval(s);
        p = t.find('[jqyoui-draggable]:last');
        u = d.$eval(t.attr('jqyoui-droppable')) || [];
        o = v.$eval(e.attr('jqyoui-draggable')) || [];
        o.index = this.fixIndex(v, o, c);
        u.index = this.fixIndex(d, u, h);
        a = angular.isArray(c) ? o.index : null;
        f = angular.isArray(c) ? c[a] : c;
        if (angular.isArray(h) && u && u.index !== undefined) {
          l = h[u.index];
        } else if (!angular.isArray(h)) {
          l = h;
        } else {
          l = {};
        }
        if (o.animate === true) {
          this.move(e, p.length > 0 ? p : t, null, 'fast', u, null);
          this.move(
            p.length > 0 && !u.multiple ? p : [],
            e.parent('[jqyoui-droppable]'),
            jqyoui.startXY,
            'fast',
            u,
            function () {
              $timeout(
                function () {
                  e.css({ position: 'relative', left: '', top: '' });
                  p.css({ position: 'relative', left: '', top: '' });
                  this.mutateDraggable(v, u, o, i, s, l, e);
                  this.mutateDroppable(d, u, o, s, f, a);
                  this.callEventCallback(d, u.onDrop, n, r);
                }.bind(this)
              );
            }.bind(this)
          );
        } else {
          $timeout(
            function () {
              this.mutateDraggable(v, u, o, i, s, l, e);
              this.mutateDroppable(d, u, o, s, f, a);
              this.callEventCallback(d, u.onDrop, n, r);
            }.bind(this)
          );
        }
      };
      this.move = function (e, t, n, r, i, s) {
        if (e.length === 0) {
          if (s) {
            window.setTimeout(function () {
              s();
            }, 300);
          }
          return false;
        }
        var o = 9999,
          u = e.offset(),
          a = t && t.is(':visible');
        if (n === null && t.length > 0) {
          if (t.attr('jqyoui-draggable') !== undefined && t.attr('ng-model') !== undefined && t.is(':visible') && i && i.multiple) {
            n = t.offset();
            if (i.stack === false) {
              n.left += t.outerWidth(true);
            } else {
              n.top += t.outerHeight(true);
            }
          } else {
            n = t.css({ visibility: 'hidden', display: 'block' }).offset();
            t.css({ visibility: '', display: a ? '' : 'none' });
          }
        }
        e.css({ position: 'absolute', 'z-index': o })
          .css(u)
          .animate(n, r, function () {
            if (s) s();
          });
      };
      this.mutateDroppable = function (e, t, n, r, i, s) {
        var o = e.$eval(r);
        e.__dragItem = i;
        if (angular.isArray(o)) {
          if (t && t.index >= 0) {
            o[t.index] = i;
          } else {
            o.push(i);
          }
          if (n && n.placeholder === true) {
            o[o.length - 1]['jqyoui_pos'] = s;
          }
        } else {
          $parse(r + ' = __dragItem')(e);
          if (n && n.placeholder === true) {
            o['jqyoui_pos'] = s;
          }
        }
      };
      this.mutateDraggable = function (e, t, n, r, i, s, o) {
        var u = angular.equals(angular.copy(s), {}),
          a = e.$eval(r);
        e.__dropItem = s;
        if (n && n.placeholder) {
          if (n.placeholder != 'keep') {
            if (angular.isArray(a) && n.index !== undefined) {
              a[n.index] = s;
            } else {
              $parse(r + ' = __dropItem')(e);
            }
          }
        } else {
          if (angular.isArray(a)) {
            if (u) {
              if (n && n.placeholder !== true && n.placeholder !== 'keep') {
                a.splice(n.index, 1);
              }
            } else {
              a[n.index] = s;
            }
          } else {
            $parse(r + ' = __dropItem')(e);
            if (e.$parent) {
              $parse(r + ' = __dropItem')(e.$parent);
            }
          }
        }
        o.css({ 'z-index': '', left: '', top: '' });
      };
      this.fixIndex = function (e, t, n) {
        if (t.applyFilter && angular.isArray(n) && n.length > 0) {
          var r = e[t.applyFilter](),
            i = r[t.index],
            s = undefined;
          n.forEach(function (e, t) {
            if (angular.equals(e, i)) {
              s = t;
            }
          });
          return s;
        }
        return t.index;
      };
    },
  ])
  .directive('jqyouiDraggable', [
    'ngDragDropService',
    function (e) {
      return {
        require: '?jqyouiDroppable',
        restrict: 'A',
        link: function (t, n, r) {
          var i, s;
          var o = function (o, u) {
            if (o) {
              i = t.$eval(n.attr('jqyoui-draggable')) || [];
              n.draggable({ disabled: false })
                .draggable(t.$eval(r.jqyouiOptions) || {})
                .draggable({
                  start: function (n, r) {
                    s = angular.element(this).css('z-index');
                    angular.element(this).css('z-index', 99999);
                    jqyoui.startXY = angular.element(this).offset();
                    e.callEventCallback(t, i.onStart, n, r);
                  },
                  stop: function (n, r) {
                    angular.element(this).css('z-index', s);
                    e.callEventCallback(t, i.onStop, n, r);
                  },
                  drag: function (n, r) {
                    e.callEventCallback(t, i.onDrag, n, r);
                  },
                });
            } else {
              n.draggable({ disabled: true });
            }
          };
          t.$watch(function () {
            return t.$eval(r.drag);
          }, o);
          o();
        },
      };
    },
  ])
  .directive('jqyouiDroppable', [
    'ngDragDropService',
    function (e) {
      return {
        restrict: 'A',
        priority: 1,
        link: function (t, n, r) {
          var i = function (i, s) {
            if (i) {
              n.droppable({ disabled: false })
                .droppable(t.$eval(r.jqyouiOptions) || {})
                .droppable({
                  over: function (n, r) {
                    var i = t.$eval(angular.element(this).attr('jqyoui-droppable')) || [];
                    e.callEventCallback(t, i.onOver, n, r);
                  },
                  out: function (n, r) {
                    var i = t.$eval(angular.element(this).attr('jqyoui-droppable')) || [];
                    e.callEventCallback(t, i.onOut, n, r);
                  },
                  drop: function (t, n) {
                    e.invokeDrop(angular.element(n.draggable), angular.element(this), t, n);
                  },
                });
            } else {
              n.droppable({ disabled: true });
            }
          };
          t.$watch(function () {
            return t.$eval(r.drop);
          }, i);
          i();
        },
      };
    },
  ]);
