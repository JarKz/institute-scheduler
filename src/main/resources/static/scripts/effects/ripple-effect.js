function ripple(event, affectingButton) {
  const buttonRectangle = event.currentTarget.getBoundingClientRect();

  const circle = document.createElement("span");
  const diameter = Math.max(buttonRectangle.width, buttonRectangle.height);
  const radius = diameter / 2;

  circle.style.width = circle.style.height = `${diameter}px`;
  circle.style.left = `${event.clientX - (buttonRectangle.left + radius)}px`;
  circle.style.top = `${event.clientY - (buttonRectangle.top + radius)}px`;
  circle.classList = ["ripple"];

  const ripple = event.currentTarget.getElementsByClassName("ripple")[0];

  if (ripple) {
    ripple.remove();
  }

  affectingButton.appendChild(circle);
}

const buttons = document.getElementsByClassName("ripple-effect");

for (const affectingButton of buttons) {
  affectingButton.addEventListener("click", (event) => ripple(event, affectingButton));
}

