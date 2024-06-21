const container = document.getElementById("calendar");
const SCHEDULES_BY_DATE = new Map();

upcoming_schedules.map(schedule => JSON.parse(schedule))
  .forEach(schedule => {
    let schedule_collection = SCHEDULES_BY_DATE.get(schedule.lesson_date);

    if (schedule_collection === undefined) {
      schedule_collection = [];
      SCHEDULES_BY_DATE.set(schedule.lesson_date, schedule_collection);
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
 * Creates pop-up window for showing the detail about schedules in particular date.
 */
function showWindow(_event, schedules) {
  const blockingWindow = document.createElement("div");
  blockingWindow.id = "blocking-window";

  const window = document.createElement("div");
  window.id = "popup-window";

  const list = document.createElement("ul");
  list.id = "schedule-list";

  schedules.sort((lhs, rhs) => lhs.lesson_start.substring(0, 2).localeCompare(rhs.lesson_start.substring(0, 2)));
  schedules.forEach(schedule => {
    const li = document.createElement("li");
    li.innerHTML = formatTime(schedule.lesson_start) + "-" + formatTime(schedule.lesson_end) + "|";
    li.classList.add("schedule-element");

    const span = document.createElement("span");
    span.innerText = schedule.subject;
    span.classList.add("schedule-subject");

    li.appendChild(span);
    list.appendChild(li);

    const linebreak = document.createElement("hr");
    linebreak.classList.add("schedule-linebreak");
    list.appendChild(linebreak);
  })

  window.appendChild(list);

  const crossSign = document.createElement("div");
  crossSign.innerHTML = "X";
  crossSign.classList.add("cross-sign");
  window.appendChild(crossSign);

  blockingWindow.appendChild(window);

  blockingWindow.addEventListener("click", _event => blockingWindow.remove());
  crossSign.addEventListener("click", _event => blockingWindow.remove());
  window.addEventListener("click", event => event.stopPropagation());

  document.body.appendChild(blockingWindow);
}

/**
 * Creates the control panel of the day element which places at bottom and contains 
 * important information and some controls like 'create new schedule at this day'.
 */
function createControlPanel(day_element, currentDate) {
  const control_panel = document.createElement("div");
  control_panel.classList.add("control-panel");

  const formattedDate = formatDate(DATE);
  if (SCHEDULES_BY_DATE.has(formattedDate)) {
    const schedules = SCHEDULES_BY_DATE.get(formattedDate);
    const countSchedules = document.createElement("h3");
    countSchedules.classList.add("count-schedules")
    countSchedules.innerText = schedules.length.toString();

    control_panel.appendChild(countSchedules);
    day_element.addEventListener("click", event => showWindow(event, schedules));
  }

  if (DATE >= currentDate && is_teacher) {
    const add_schedule = document.createElement("div");
    add_schedule.classList.add("add-schedule");
    const dayDate = new Date(DATE);
    add_schedule.addEventListener("click", event => {
      window.location.href = "/schedule-creator?lesson-date=" + formatDate(dayDate);
      event.stopPropagation();
    });
    control_panel.appendChild(add_schedule);
  }

  return control_panel;
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

  const currentDate = new Date();
  const currentMonth = currentDate.getMonth();
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

  element.appendChild(createControlPanel(element, currentDate));

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
