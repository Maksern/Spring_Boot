document.onmousemove = function (event) {
    let x_left = event.x - 815;
    let y_left = event.y - 410;

    let x_right = event.x - 850;
    let y_right = event.y - 410;

    document.querySelector(".left-eye").style.transform = "rotate("+arctg(x_left, y_left)+"deg)";
    document.querySelector(".right-eye").style.transform = "rotate("+arctg(x_right, y_right)+"deg)";

    function arctg(x, y) {
      if (x >= 0) {
        return 180/Math.PI * Math.atan(y / x);
      } else {
        return 180/Math.PI * (Math.atan(y / x) + Math.PI);
      }
    }

  }