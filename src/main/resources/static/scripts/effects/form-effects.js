function bindEffects(inputElement) {
  inputElement.addEventListener("focusin", (event) => {
    const effectElement = document.createElement("span");
    let rectangle = event.currentTarget.getBoundingClientRect();
    let rectangleFormPage = event.currentTarget.parentNode.getBoundingClientRect();
    effectElement.style.top = `${rectangle.top - rectangleFormPage.top}px`;
    effectElement.style.left = `${rectangle.left - rectangleFormPage.left}px`;
    event.currentTarget.parentNode.insertBefore(effectElement, event.currentTarget.nextSibling);
    effectElement.id = "color-outline"
    effectElement.classList = ["show-color-outline"];
  }, false);

  inputElement.addEventListener("focusout", () => {
    let allEffectElements = document.getElementsByClassName("show-color-outline");
    for (let element of allEffectElements) {
      element.classList = ["erase-color-outline"];
      setTimeout(() => {
        element.remove();
      }, 400);
    }
  }, false);
}

const inputElements = document.getElementsByTagName("input");

for (const inputElement of inputElements) {
  bindEffects(inputElement);
}

const selectElements = document.getElementsByTagName("select");

for (const selectElement of selectElements) {
  bindEffects(selectElement);
}

const errorMessage = document.getElementsByClassName("error");

if (errorMessage.length !== 0) {
  const inputElementsForSetBorderColor = document.getElementsByTagName("input");
  for (const element of inputElementsForSetBorderColor) {
    element.style.borderColor = "rgba(255, 0, 0, 0.9)";
  }

  const selectElementsForSetBorderColor = document.getElementsByTagName("select");
  for (const element of selectElementsForSetBorderColor) {
    element.style.borderColor = "rgba(255, 0, 0, 0.9)";
  }
}

