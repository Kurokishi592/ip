# Kurokishi — User Guide

Kurokishi is a **command-line chatbot** that helps you manage tasks quickly from your terminal. It supports todos, deadlines, and events with date and optional time, searching, and automatic saving to disk.

---

## Table of Contents

- [Quick Start](#quick-start)
- [Command Summary](#command-summary)
- [Features](#features)
  - [list](#list)
  - [add](#add)
  - [todo](#todo)
  - [deadline](#deadline)
  - [event](#event)
  - [mark](#mark)
  - [unmark](#unmark)
  - [delete](#delete)
  - [find](#find)
  - [bye](#bye)
- [Saving Data](#saving-data)
- [Editing Data Files](#editing-data-files)
- [Archiving Data Files](#archiving-data-files)
- [FAQ](#faq)
- [Known Issues](#known-issues)

---

## Quick Start

1. Ensure you have the prerequisites in your system.
    - Java 17 or newer (JDK).
    - A terminal (Windows PowerShell/CMD) or an IDE (e.g., VS Code, intellij).
    - Mac users: Ensure you have the precise JDK version prescribed [here](https://se-education.org/guides/tutorials/javaInstallationMac.html)

2. Download/Clone (either method)
    - Download the latest `.jar` file from the latest release [here](https://github.com/Kurokishi592/ip/releases)
    OR
    - Clone this repo and enter the project directory:
        - `git clone https://github.com/Kurokishi592/ip.git`
        - `cd ip/ip`

3. Launch Kurokishi using either of these 3 methods:

| VSCode                                                                                                                                                                       | Intellij                                                                                                                                                                                                                                                                                                                                                                                              | Terminal                                                                                                                                 |
| ---------------------------------------------------------------------------------------------------------------------------------------------------------------------------- | ----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- | ---------------------------------------------------------------------------------------------------------------------------------------- |
| - Open the project folder in VS Code.<br><br>- Open the file `src/main/java/kurokishi/Kurokishi.java`.<br><br>- Run the main method using “Run” or the code lens above main. | 1. Open the project into Intellij as follows:<br><br>   1. Click `Open`.<br><br>   2. Select the project directory, and click `OK`.<br><br>   3. If there are any further prompts, accept the defaults.<br><br>2. Locate the `src/main/java/kurokishi/Kurokishi.java` file, right-click it, and choose `Run Kurokishi.main()` (if the code editor is showing compile errors, try restarting the IDE). | Open a command terminal, cd into the folder you put the jar file in, and use the java -jar Kurokishi.jar command to run the application. |


>[!Note] Tasks are saved automatically to `data/tasks.txt` in the `ip` folder.
> Every valid command will save the task immediately and automatically.

Witness the bootup of Kurokishi, which should display the following in your terminal:
![Screenshot of Kurokishi's startup](/ip/docs/startup.png)

4. Type a valid command from this [summary of commands]((#command-summary)) whenever prompted and press Enter to execute it. 

5. Refer to [Features](#features) below for the details of commands available. 
---

## Command Summary

| Action / Feature | Format / Command | Examples |
| --- | --- | --- |
| List all tasks | `list` | `list` |
| Add a task | ``add <description>`` | `add sleep well` |
| Add a todo | ``todo <description>`` | `todo read book` |
| Add a deadline (date or date+time) | ``deadline <description> /by <yyyy-MM-dd[ HHmm]>`` | `deadline return book /by 2019-10-15`, `deadline return book /by 2025-10-15 1800` |
| Add an event (date or date+time range) | ``event <description> /from <yyyy-MM-dd[ HHmm]> /to <yyyy-MM-dd[ HHmm]>`` | `event trip /from 2025-10-01 /to 2025-10-05`, `event meeting /from 2025-10-01 0930 /to 2025-10-01 1730` |
| Mark a task done | ``mark <index>`` | `mark 2` |
| Mark a task not done | ``unmark <index>`` | `unmark 2` |
| Delete a task | ``delete <index>`` | `delete 3` |
| Find tasks by keyword | ``find <keyword>`` | `find book` |
| Exit the app | `bye` | `bye` |

Supported date/time inputs:
- Date only: `yyyy-MM-dd` (e.g., `2025-10-15`)
- Date + time: `yyyy-MM-dd HHmm` (e.g., `2025-10-15 0930`)

Display:
- Date-only inputs are shown as a date (e.g., `15 Oct 2025`).
- Date+time inputs are shown with time (e.g., `15 Oct 2025 0930`).

---

## Features

>[!tick] Please take note of the format of inputs
> Commands are case insensitive
> Date format is important
> Any input that does not follow the required format will throw an error with corresponding prompt to fix

### list
Shows all tasks.

Example:
```
list
```

Sample output:
```
------------------------------------------------------------
[SYSTEM NOTICE] Compiling list of active tasks:
1.[T][ ] read book
2.[D][ ] return book (by: 15 Oct 2025)
3.[E][X] conference (from: 1 Oct 2025 0930 to: 1 Oct 2025 1730)
------------------------------------------------------------
[SYSTEM NOTICE] Ready for next command. Glory to Humanity!
```

---
### add
Adds a normal tasks with a description

Format:
```
add <description>
```

Example:
```
add sleep well
```

Sample output:
```
------------------------------------------------------------
    [SYSTEM NOTICE] Item has been registered in memory: [N][ ] sleep well
------------------------------------------------------------
[SYSTEM NOTICE] Ready for next command. Glory to Humanity!
```

>[!tip] 
>Keep descriptions short and specific.

---

### todo
Adds a todo with a description.

Format:
```
todo <description>
```

Example:
```
todo read book
```

Sample output:
```
------------------------------------------------------------
    [SYSTEM NOTICE] Todo task added successfully.
          [T][ ] read book
    [STATUS] Current number of active tasks: 7
------------------------------------------------------------
[SYSTEM NOTICE] Ready for next command. Glory to Humanity!
```


---

### deadline
Adds a deadline with date-only or date+time.

Format:
```
deadline <description> /by <yyyy-MM-dd[ HHmm]>
```

Examples:
```
deadline return book /by 2025-10-15
deadline return book /by 2025-10-15 1800
```

Sample output:
```
------------------------------------------------------------
    [SYSTEM NOTICE] Deadline task added successfully.
          [D][ ] return books (by: 15 Oct 2025 1800)
    [STATUS] Current number of active tasks: 8
------------------------------------------------------------
[SYSTEM NOTICE] Ready for next command. Glory to Humanity!
```

>[!note] 
> If you provide a date without time, the time defaults to 00:00 (midnight).

---

### event
Adds an event with start and end date/time. End must not be before start.

Format:
```
event <description> /from <yyyy-MM-dd[ HHmm]> /to <yyyy-MM-dd[ HHmm]>
```

Examples:
```
event trip /from 2025-10-01 /to 2025-10-05
event meeting /from 2025-10-01 0930 /to 2025-10-01 1730
```

Sample output:
```
------------------------------------------------------------
    [SYSTEM NOTICE] Event task added successfully.
          [E][ ] meeting (from: 1 Oct 2025 0930 to: 1 Oct 2025 1730)
    [STATUS] Current number of active tasks: 9
------------------------------------------------------------
[SYSTEM NOTICE] Ready for next command. Glory to Humanity!
```

>[!note] 
> If you provide a date without time, the time defaults to 00:00 (midnight).
> the date and time after "to" must be chronologically after the date and time after "from"

---

### mark
Marks a task as done by index (1-based).

Format:
```
mark <index>
```

Example:
```
mark 7
```

Sample output:
```
------------------------------------------------------------
    [SYSTEM UPDATE] Task status: marked as complete.
    [T][X] read book
------------------------------------------------------------
[SYSTEM NOTICE] Ready for next command. Glory to Humanity!
```

---

### unmark
Marks a task as not done by index (1-based).

Format:
```
unmark <index>
```

Example:
```
unmark 7
```

Sample output:
```
------------------------------------------------------------
    [SYSTEM UPDATE] Task status: reverted to incomplete.
    [T][ ] read book
------------------------------------------------------------
[SYSTEM NOTICE] Ready for next command. Glory to Humanity!
```

---

### delete
Deletes a task by index (1-based).

Format:
```
delete <index>
```

Example:
```
delete 7
```

Sample output:
```
------------------------------------------------------------
    [SYSTEM NOTICE] Event task deleted successfully.
          [T][ ] read book
    [STATUS] Current number of active tasks: 8
------------------------------------------------------------
[SYSTEM NOTICE] Ready for next command. Glory to Humanity!
```

>[!tick] Tasklist will automatically move up the tasks below deleted task upwards
> to check the indexes of your tasks, it is adviced to `list` once before and after issuing the `delete` command

---

### find
Finds tasks with descriptions containing the keyword (case-insensitive).

Format:
```
find <keyword>
```

Example:
```
find book
```

Sample output:
```
------------------------------------------------------------
    Here are the matching tasks in your list:
     1.[D][ ] return books (by: 15 Oct 2025)
     2.[E][ ] read books (from 1 Oct 2025 0930 to: 1 Oct 2025 1730 )
------------------------------------------------------------
[SYSTEM NOTICE] Ready for next command. Glory to Humanity!
```

---

### bye
Exits the application.

Format:
```
bye
```

Sample output:
```
------------------------------------------------------------
[SYSTEM NOTICE] Session concluded. Probability of future contact: high. Glory to Humanity.
------------------------------------------------------------
```

---

## Saving Data

- Kurokishi automatically saves after changes (e.g., add, delete, mark/unmark).
- Save file location: `ip\data\tasks.txt` (relative to project root).
- Dates are stored in ISO format (e.g., `2025-10-15T18:00`).  
  Events may be stored either as separate fields or a single `from - to` field depending on version; both are recognized when loading.

Example file contents:
```
T | 0 | read book
D | 1 | return book | 2019-10-15T18:00
E | 0 | meeting | 2025-10-01T09:30 | 2025-10-01T17:30
E | 0 | trip | 2025-10-01T00:00 - 2025-10-05T00:00
```

>[!note] 
>`1` means done, `0` means not done.

---

## Editing Data Files

You can edit `data/tasks.txt` manually if needed.

- Keep the structure and separators exactly as shown:
  - Fields are separated by `|` (pipe) with optional spaces.
  - For events, either use two fields for start and end, or one field in the form `start - end`.
- Use valid ISO date-time (`yyyy-MM-ddTHH:mm`) or date-only (`yyyy-MM-dd`) values.
- Make a backup before editing.

Example valid edits:
```
D | 0 | file taxes | 2025-04-15T00:00
E | 1 | seminar | 2025-10-10T09:00 - 2025-10-10T12:00
```

>[!warning] 
> Invalid lines may cause the app to skip tasks or show a “Corrupted data” error.
> You may need to fully clear the file for the program to work again.

---

## Archiving Data Files

To archive your current tasks:
1. Close the app.
2. Copy `ip\data\tasks.txt` to a safe location (e.g., `archive/tasks-2025-10-01.txt`).
3. To start fresh, delete `ip\data\tasks.txt` and run the app; a new file will be created automatically.

To restore an archive:
1. Replace `ip\data\tasks.txt` with your archived file while the app is not running.
2. Start the app.

---

## FAQ

- Q: What date/time formats are supported?  
  A: `yyyy-MM-dd` and `yyyy-MM-dd HHmm` (e.g., `2019-10-15 0930`).

- Q: Why does my event show only a date and no time?  
  A: If you enter a date without time, it’s stored as 00:00 and shown as a date.

- Q: How do I reset all tasks?  
  A: Delete `ip\data\tasks.txt` while the app is closed. When you restart, the file is recreated.

- Q: I edited `tasks.txt` and now loading fails.  
  A: Check that all lines match the expected formats and that dates are valid.

- Q: Where does the app store data?  
  A: `ip\data\tasks.txt` relative to the project’s `ip` folder.

---

## Known Issues

- Exact spacing around markers is required for commands:
  - Use space before and after markers, e.g., `event x /from 2025-10-01 /to 2025-10-02` (note the spaces).
- Invalid dates (e.g., month `13`) will be rejected with an error.
- Very large data files may slow down “list” output in some terminals.

---
