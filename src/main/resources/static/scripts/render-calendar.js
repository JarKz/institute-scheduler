const container = document.getElementById("calendar");
const SCHEDULES_BY_DATE = new Map();

upcoming_schedules.map(schedule => JSON.parse(schedule))
  .forEach(schedule => {
  let schedule_collection = SCHEDULES_BY_DATE.get(schedule["lesson_date"]);

  if (schedule_collection === undefined) {
    schedule_collection = [];
    SCHEDULES_BY_DATE.set(schedule["lesson_date"], schedule_collection);
  }

  schedule_collection.push(schedule);
});

/**
 * Formats the JS Date object to local date ISO format like "YYYY-mm-dd".
 */
function formatDate(date) {
  const year = date.getFullYear();
  const month = date.getMonth() + 1;
  const day = date.getDate();

  let formattedDate = year.toString() + "-";

  if (month <= 9) {
    formattedDate += "0";
  }
  formattedDate += month + "-";

  if (day <= 9) {
    formattedDate += "0";
  }
  formattedDate += day;
  return formattedDate;
}

/**
 * Formats the string time from "HH:mm:ss" to "HH:mm".
 */
function formatTime(time) {
  // Because of the time is always in ISO time format, so this only cuts seconds.
  return time.substring(0, 5);
}


/**
 * Creates the integer that represent the count of the calendar rows. 
 * It's neccessary because of edge case where instead of standard 
 * 5 rows may return only 4 rows.
 */
function getCalendarRows() {
  const date = new Date();
  const totalDaysInMonth = new Date(date.getFullYear(), date.getMonth() + 1, 0).getDate();
  const isMonday = new Date(date.getFullYear(), date.getMonth(), 1).getDay() == 1;

  // The specific edge case when the Feburary has only 28 days and starts at Monday, so the count of rows in total will be equal to 4.
  if (totalDaysInMonth == 28 && isMonday) {
    return 4;
  }

  return 5;
}

/**
 * Creates the Date object of start the virtual current month. 
 * It unneccessary will be start of a month, may be a little bit earlier than it.
 * It is neccessary to use for creating calendar elements.
 */
function startOfCalendar() {
  const rotateWeek = (week) => {
    week -= 1;
    if (week == -1) {
      week = 6;
    }
    return week;
  };

  const date = new Date();
  const startOfMonth = new Date(date.getFullYear(), date.getMonth(), 1);
  const weekDay = rotateWeek(startOfMonth.getDay());

  const startOfCalendar = startOfMonth;
  if (weekDay > 0) {
    startOfCalendar.setDate(startOfCalendar.getDate() - weekDay);
  }

  return startOfCalendar;
}

/**
 * Increments a date by one day.
 * 
 * JS will do a lot of work in backend.
 */
function incrementDate(date) {
  date.setDate(date.getDate() + 1);
}

/**
 * Creates the row of the calendar named 'week'. It's neccessary because of styling.
 */
function createWeekElement() {
  const element = document.createElement("div");
  element.classList.add("week");
  return element;
}

/**
 * The specific element for creating the calendar day elements.
 */
const DATE = startOfCalendar();

/**
 * Creates the day element in row. Uses the const DATE, which helps determine
 * whether day is in current month or not.
 *
 * Also adds the inner elements into this like header and numbered list.
 */
function createDayElement() {
  const element = document.createElement("div");

  const currentMonth = new Date().getMonth();
  if (DATE.getMonth() != currentMonth) {
    element.classList.add("disabled-day");
  } else {
    element.classList.add("day");
  }

  const header = document.createElement("h3");
  header.innerHTML = DATE.toLocaleDateString("en", {
    month: "long",
    day: "numeric"
  });
  header.classList.add("day-header")
  element.appendChild(header);

  const ol = document.createElement("ol");
  ol.id = "event-list";

  const formattedDate = formatDate(DATE);
  if (SCHEDULES_BY_DATE.has(formattedDate)) {
    SCHEDULES_BY_DATE.get(formattedDate)
      .forEach(schedule => {
        const li = document.createElement("li");
        li.innerText = schedule["subject"] + ", " + formatTime(schedule["lesson_start"]) + "-" + formatTime(schedule["lesson_end"]);
        li.classList.add("schedule-list");
        ol.appendChild(li);
      })
  }

  element.appendChild(ol);

  return element;
}

const totalRows = getCalendarRows();

for (let row = 0; row < totalRows; row++) {
  const week = createWeekElement();
  for (let column = 0; column < 7; column++) {
    week.appendChild(createDayElement());
    incrementDate(DATE);
  }

  container.appendChild(week);
}
