const lesson_date = document.getElementById("lesson-date");
const date = new Date(lesson_date.innerHTML);

lesson_date.innerHTML = "Date: " + date.toLocaleDateString("en", {
  month: "long",
  day: "numeric",
  year: "numeric"
});

function formatPhoneNumber(phone_number) {
  return "+" + phone_number.substring(0, 3) + "-" + phone_number.substring(3, 5) + "-" + phone_number.substring(5, 8) + "-" + phone_number.substring(8, 10) + "-" + phone_number.substring(10, 12);

}

function formatPersonInformation(person) {
  const first_name = person.getElementsByClassName("first-name").item(0);
  const patronymic = person.getElementsByClassName("patronymic").item(0);
  const last_name = person.getElementsByClassName("last-name").item(0);

  const acronym = document.createElement("span");
  acronym.innerHTML = first_name.innerHTML.substring(0, 1) + ". " + patronymic.innerHTML.substring(0, 1) + ". " + last_name.innerHTML;

  const phone_number = person.getElementsByClassName("phone-number").item(0);

  const phone = document.createElement("span");
  phone.innerHTML = formatPhoneNumber(phone_number.innerHTML);

  return [acronym, phone];
}

const teacher = document.getElementById("teacher");

const teacher_title = document.createElement("span");
teacher_title.innerHTML = "The teacher: ";
const teacher_info = formatPersonInformation(teacher);

teacher.innerHTML = "";

teacher.appendChild(teacher_title);
teacher_info.forEach(element => teacher.appendChild(element));


const students = document.getElementById("students");
for (const student of students.children) {
  const student_info = formatPersonInformation(student);
  student.innerHTML = "";
  student_info.forEach(element => student.appendChild(element));
}
