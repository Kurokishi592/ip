# Kurokishi — User Guide

Kurokishi is a **CLI-based chatbot** that helps you manage tasks from your terminal. It supports todos, deadlines, and events, with dates and optional times, quick search, and auto-saving to disk.

---

## Getting Started

### Prerequisites
- Java 17 or newer (JDK).
- A terminal (Windows PowerShell/CMD recommended) or an IDE (e.g., VS Code or Intellij).

### Download/Clone
- Clone this repo:
  - `git clone https://github.com/Kurokishi592/ip.git`
  - `cd ip/ip`
- Or, download the latest `.jar` file from the latest release.

### Run from VS Code (recommended)
1. Open the project folder in VS Code.
2. Open the file `src/main/java/kurokishi/Kurokishi.java`.
3. Run the main method using “Run” or the code lens above main.

### Run from Intellij
1. Open the project into Intellij as follows:
   1. Click `Open`.
   2. Select the project directory, and click `OK`.
   3. If there are any further prompts, accept the defaults.
2. Locate the `src/main/java/kurokishi/Kurokishi.java` file, right-click it, and choose `Run Kurokishi.main()` (if the code editor is showing compile errors, try restarting the IDE).

### Run from Terminal
1. Open a command terminal, cd into the folder you put the jar file in, and use the java -jar Kurokishi.jar command to run the application.

Data is saved automatically to `data/tasks.txt` under the `ip` folder.

A successful run will activate Kurokishi with the following display:

```
 _  __  
| |/ / 
| ' /   
| . \
|_|\\_\
 _    _  
| |  | |
| |  | |
| |__| |
 \____/
 ____   
|  _ \  
| |_) | 
|  _ <  
|_| \\_\
  ____
 / __ \ 
| |  | | 
| |__| |
 \____/  

Unit Kurokishi activated.
My role is to support you in organizing your tasks.

>> SYSTEM DIRECTIVE: Awaiting your command...
>>     Use 'add <description>' to register a new item.
>>     Use 'delete <task number>' to trash a memory.
>>     Use 'list' to review all stored records.
>>     Use 'mark <task number>' to confirm task completion.
>>     Use 'unmark <task number>' to revoke completion status.
>>     Use 'todo <description>' to log a standard task.
>>     Use 'deadline <description> /by <yyyy-MM-dd HHmm>' to establish a time-bound objective.
>>     Use 'event <description> /from <yyyy-MM-dd HHmm> /to <yyyy-MM-dd HHmm>' to schedule an occurrence.
>>     Use 'find <keyword>' to search your records.
>>     Use 'bye' to terminate the current connection.
>> SYSTEM DIRECTIVE: Standing by for your command.
```

---

## Features

- Add: A simple task.
- Todos: Todo tasks to track but without a date.
- Deadlines: Tasks due by a specific date or date-time.
- Events: Tasks spanning a start and end date or date-time.
- List: View all tasks.
- Mark/Unmark: Toggle completion.
- Delete: Remove tasks.
- Find: Search tasks by keyword.
- Auto-save: Changes persist to `data/tasks.txt`.
- Exit: Close the bot. Tasks will be saved and reloaded the next time bot is activated.

Note of the supported date/time inputs (both acceptable):
- Date only: `yyyy-MM-dd` (e.g., `2019-10-15`)
- Date + time: `yyyy-MM-dd HHmm` (e.g., `2019-10-15 0930`)

Display on Terminal are in Diegetic System Notification style, with:
- Prompts for users
- Error messages (if any)
- Notification of user commands executed.
- Display of the output of respective commands executed.
- Dates show as a friendly date (e.g., `Oct 15 2019`) or date-time (`Oct 15 2019 0930`).
- Date-only inputs are treated as midnight time (i.e. `0000`)

---

## Usage

### Command Reference

| Command  | Format                                                                   | Example                                                      |
| -------- | ------------------------------------------------------------------------ | ------------------------------------------------------------ |
| list     | `list`                                                                   | `list`                                                       |
| add      | `add <description>`                                                      | `add sleep well`                                             |
| todo     | `todo <description>`                                                     | `todo read book`                                             |
| deadline | `deadline <description> /by <yyyy-MM-dd[ HHmm]>`                         | `deadline return book /by 2019-10-15`                        |
| event    | `event <description>; /from <yyyy-MM-dd[ HHmm]> /to <yyyy-MM-dd[ HHmm]>` | `event conference /from 2025-10-01 0930 /to 2025-10-01 1730` |
| mark     | `mark <index>`                                                           | `mark 2`                                                     |
| unmark   | `unmark <index>`                                                         | `unmark 2`                                                   |
| delete   | `delete <index>`                                                         | `delete 3`                                                   |
| find     | `find <keyword>`                                                         | `find book`                                                  |
| bye      | `bye`                                                                    | `bye`                                                        |

Notes:
- Indices are 1-based (as shown in list output).
- For events, the end must not be before the start.

### Examples

- Add a todo:
  - `todo read book`
- Add a deadline (date only):
  - `deadline return book /by 2019-10-15`
- Add a deadline (date + time):
  - `deadline return book /by 2019-10-15 1800`
- Add an event (date only):
  - `event trip /from 2025-10-01 /to 2025-10-05`
- Add an event (date + time):
  - `event meeting /from 2025-10-01 0930 /to 2025-10-01 1730`
- Mark, unmark, delete:
  - `mark 1`
  - `unmark 1`
  - `delete 1`
- Find:
  - `find book`
- Exit:
  - `bye`

---

## FAQ / Troubleshooting

- Invalid date/time
  - Use `yyyy-MM-dd` or `yyyy-MM-dd HHmm` (e.g., `2019-10-15 0930`).
  - Check that the month and day are valid (e.g., month 01–12).

- Event end before start
  - Ensure the `/to` time is the same as or after `/from`.

- Nothing happens when running
  - Ensure Java is installed: `java -version` and `javac -version`.
  - Recompile if you changed code:
    - `dir /s /b src\main\java\*.java > sources.txt`
    - `javac -d bin @sources.txt`
    - `java -cp bin kurokishi.Kurokishi`

- Where are my tasks saved?
  - `ip\data\tasks.txt`. Delete this file to reset all tasks.

- “Class not found” when running
  - Ensure you are in the `ip` folder and used `-cp bin`:
    - `java -cp bin kurokishi.Kurokishi`

---

## Command Summary

```
list
add <description>
todo <description>
deadline <description> /by <yyyy-MM-dd[ HHmm]>
event <description> /from <yyyy-MM-dd[ HHmm]> /to <yyyy-MM-dd[ HHmm]>
mark <index>
unmark <index>
delete <index>
find <keyword>
bye
```

---
