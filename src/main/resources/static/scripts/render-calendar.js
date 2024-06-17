const container = document.getElementById("calendar");


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
