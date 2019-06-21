// export var name = '张三';
const name1= '李四';
const name2= '李四2';
function add(x,y) {
  console.log(x+y);
}
// export {name1,name2,add}
// export {name1};
// export {name2};
// export {add};

export default {
  name1,
  name2,
  add
}
