# Callio Mobile

**A mobile-first contact management and calling application built with Kotlin and Jetpack Compose.**

Callio is an Android application designed to simplify contact management, streamline business calls, and keep track of customer interactions.

The initial goal is to provide a practical, intuitive, and offline-first tool for managing contacts and making calls. The project starts as a standalone mobile application, with the possibility of evolving into a multi-tenant CRM SaaS platform in the future.

---

## Table of Contents

- [Project Vision](#project-vision)
- [Goals](#goals)
- [Technology Stack](#technology-stack)
- [Architecture](#architecture)
- [Project Structure](#project-structure)
- [Core Features](#core-features)
- [User Experience](#user-experience)
- [Local Data Storage](#local-data-storage)
- [Contact Management](#contact-management)
- [Calling and Dual SIM Support](#calling-and-dual-sim-support)
- [Offline-First Strategy](#offline-first-strategy)
- [Future Backend Integration](#future-backend-integration)
- [Development Roadmap](#development-roadmap)
- [Development Principles](#development-principles)
- [Project Status](#project-status)

---

## Project Vision

Callio aims to provide a simple and reliable way to manage business contacts and customer interactions directly from an Android device.

The application will initially operate without a backend, storing its data locally on the device.

This approach allows the project to focus on its most important aspects:

- A clean and intuitive user experience.
- Reliable contact management.
- Easy access to phone calls.
- Local data persistence.
- A maintainable architecture.
- A foundation for future CRM capabilities.

The long-term vision is to evolve Callio into a CRM platform where companies can manage employees, customers, assignments, and interaction history.

## Goals

### Initial goals

- Manage business contacts.
- Import selected contacts from the Android address book.
- Store contact information locally.
- Make calls directly from the application.
- Support devices with Dual SIM functionality through Android's telephony capabilities.
- Keep track of customer interactions.
- Add notes and follow-up information.
- Provide an intuitive and accessible interface.

### Long-term goals

- User authentication.
- Company and employee management.
- Shared company contacts.
- Contact assignments.
- Centralized interaction history.
- Multi-device synchronization.
- Web administration panel.
- Multi-tenant CRM SaaS capabilities.

---

## Technology Stack

| Technology | Purpose |
|---|---|
| Kotlin | Primary programming language |
| Jetpack Compose | Declarative UI |
| Material 3 | Design system |
| Room | Local database |
| SQLite | Local data persistence |
| Coroutines | Asynchronous operations |
| Flow / StateFlow | Reactive state management |
| ViewModel | UI state and presentation logic |
| Hilt | Dependency injection |
| Navigation Compose | Application navigation |
| DataStore | User preferences |
| Kotlin Serialization | Future API serialization |
| Android Contacts Provider | Device contact integration |
| Android Telecom / Intents | Phone calling integration |

The initial implementation will prioritize Android-native capabilities and avoid unnecessary external dependencies.

---

## Architecture

Callio will use a **feature-first architecture**, combining MVVM, Repository Pattern, and a lightweight implementation of Clean Architecture.

The objective is to maintain clear responsibilities without introducing unnecessary abstractions.

### Architectural layers

Each feature can be divided into three main layers:

**Presentation**
- Jetpack Compose screens.
- UI components.
- ViewModels.
- UI state.
- User interactions.

**Domain**
- Business models.
- Repository contracts.
- Use cases.
- Business rules.

**Data**
- Room entities.
- DAOs.
- Repository implementations.
- Data mappers.
- Local data sources.

### Data flow

```text
Compose Screen
      |
      v
   ViewModel
      |
      v
    UseCase
      |
      v
Repository Interface
      |
      v
Repository Implementation
      |
      v
   Room Database
```

This separation allows the application to change its data source in the future without coupling the presentation layer to a specific implementation.

For the initial version, the architecture will live inside a single Android application module. Additional Gradle modules can be extracted when the project genuinely needs them.

---

## Project Structure

Proposed package organization:

```text
callio-mobile/
│
├── app/
│   └── src/main/java/com/callio/app/
│       ├── CallioApplication.kt
│       └── MainActivity.kt
│
├── core/
│   ├── common/
│   ├── database/
│   ├── datastore/
│   ├── designsystem/
│   ├── navigation/
│   ├── permissions/
│   └── telephony/
│
├── feature/
│   ├── home/
│   ├── contacts/
│   ├── contact_detail/
│   ├── calls/
│   ├── favorites/
│   └── settings/
│
├── gradle/
│
├── docs/
│   └── ARCHITECTURE.md
│
├── build.gradle.kts
├── settings.gradle.kts
├── gradle.properties
├── gradlew
└── README.md
```

### Feature example

```text
feature/contacts/
│
├── presentation/
│   ├── ContactsScreen.kt
│   ├── ContactsViewModel.kt
│   ├── ContactsUiState.kt
│   └── components/
│
├── domain/
│   ├── model/
│   ├── repository/
│   └── usecase/
│
└── data/
    ├── local/
    ├── mapper/
    └── repository/
```

---

## Core Features

### 1. Home

The main dashboard provides quick access to the application's most important functionality.

Initial components:

- Welcome section.
- Contact statistics.
- Quick actions.
- Recently accessed contacts.
- Recent interactions.

### 2. Contacts

Contact management is the central feature of Callio.

Capabilities:

- Create contacts.
- Edit contact information.
- Delete locally managed contacts.
- Search contacts.
- View contact details.
- Manage phone numbers.
- Add notes.
- Mark contacts as favorites.
- Import selected contacts from the device.

Deleting a contact from Callio must not delete it from the Android address book.

### 3. Contact Details

Each contact should have a dedicated screen containing:

- Name.
- Company.
- Phone numbers.
- Email address.
- Notes.
- Favorite status.
- Interaction history.
- Quick calling actions.

### 4. Calls

The application will provide a dedicated section for customer interactions.

Initial capabilities:

- Start a phone call.
- Associate an interaction with a contact.
- Record call outcomes manually.
- Add notes after a call.
- Review previous interactions.

The MVP will not depend on unrestricted access to Android's native call history.

### 5. Favorites

A dedicated section for frequently contacted customers.

### 6. Settings

Initial configuration options:

- Theme selection.
- Application preferences.
- Contact permissions.
- Data management.
- Application information.

---

## User Experience

Callio will prioritize simplicity and accessibility.

### Main navigation

The application will use a bottom navigation bar:

| Section | Purpose |
|---|---|
| Home | Overview and quick actions |
| Contacts | Contact management |
| Calls | Interaction history |
| Favorites | Frequently accessed contacts |
| Settings | Application configuration |

### Design principles

- Material 3.
- Light and dark themes.
- Consistent spacing and typography.
- Clear visual hierarchy.
- Short and intuitive forms.
- Accessible controls.
- Responsive layouts.
- Meaningful loading, empty, and error states.

The initial application language will be English.

---

## Local Data Storage

Callio will use Room as its persistence layer, backed by SQLite.

### Initial data model

The first version should avoid excessive normalization or unnecessary entities.

**Contact**

| Field | Description |
|---|---|
| id | Unique identifier |
| name | Contact name |
| company | Associated company |
| email | Email address |
| notes | General notes |
| isFavorite | Favorite status |
| createdAt | Creation timestamp |
| updatedAt | Last update timestamp |

**ContactPhone**

| Field | Description |
|---|---|
| id | Unique identifier |
| contactId | Associated contact |
| number | Phone number |
| label | Mobile, Work, Home, etc. |

**CallInteraction**

| Field | Description |
|---|---|
| id | Unique identifier |
| contactId | Associated contact |
| date | Interaction timestamp |
| direction | Incoming or outgoing |
| outcome | Answered, missed, follow-up, etc. |
| notes | Interaction notes |
| duration | Optional duration |

The exact schema may evolve as the application develops.

### Important considerations

- Use UUIDs for identifiers where practical.
- Keep timestamps for future synchronization.
- Store phone numbers in a normalized format when possible.
- Avoid storing unnecessary copies of device information.
- Preserve user data when updating the database schema through migrations.

---

## Contact Management

Callio will distinguish between two types of contact information:

**Android contacts**

Contacts provided by the device through Android's Contacts Provider.

**Callio contacts**

Contacts managed and stored inside the application's Room database.

The user should explicitly grant permission before Callio accesses the device address book.

The import process should allow the user to select which contacts to import instead of automatically copying the entire address book.

This distinction is important because Callio may eventually manage business information that does not exist in the device's address book.

---

## Calling and Dual SIM Support

Callio will use Android's native calling capabilities.

### Initial implementation

Use `Intent.ACTION_DIAL` to open the system dialer with the selected phone number.

This approach:

- Does not require the `CALL_PHONE` permission.
- Allows the user to review the number before calling.
- Uses Android's native calling interface.
- Leaves SIM selection to the Android device and its system configuration.

### Future improvements

Direct calling through `Intent.ACTION_CALL` may be evaluated later.

This requires the appropriate permission and additional testing.

Dual SIM behavior depends on Android versions, device manufacturers, and telephony implementations. Therefore, Callio should not promise universal control over SIM selection.

Native call log access will also be treated separately because Android permissions and Google Play policies restrict access to call history.

---

## Offline-First Strategy

The initial application will be fully usable without an internet connection.

Room will act as the primary data source.

This means:

- Contacts remain available offline.
- Contact changes are stored locally.
- Interaction history is stored locally.
- No account is required initially.
- No backend connection is necessary.

The initial version will operate as a single-device application.

There will be no centralized sharing between employees or synchronization across devices until backend integration is implemented.

---

## Future Backend Integration

Callio may evolve into a multi-tenant CRM platform using Laravel as its backend.

The Android application should be designed so that its data access layer can eventually communicate with a remote API.

### Proposed future backend capabilities

- Authentication.
- Companies and organizations.
- Employee management.
- Role-based access control.
- Shared contacts.
- Contact assignments.
- Call and interaction tracking.
- Centralized data management.
- Synchronization between devices.

### Potential domain model

```text
Company
  |
  ├── Users
  |
  └── Contacts
        |
        ├── ContactPhones
        ├── ContactAssignments
        ├── Notes
        └── CallInteractions
```

A contact may belong to a company and be assigned to one or more employees.

This allows multiple employees to work with the same customer without maintaining unrelated copies of the same company contact.

### Synchronization considerations

The future synchronization strategy should consider:

- UUID-based identifiers.
- `created_at` and `updated_at`.
- Local synchronization status.
- Conflict resolution.
- Soft deletes or deletion tombstones.
- Authentication and tenant isolation.

Room can remain as the local database and offline cache after the backend is introduced.

---

## Development Roadmap

Development will be incremental, prioritizing functional features over premature complexity.

| Sprint | Objective |
|---|---|
| 01 | Project setup and architecture |
| 02 | Design system and navigation |
| 03 | Room database and initial models |
| 04 | Android contacts integration |
| 05 | Contact CRUD operations |
| 06 | Calling functionality and Dual SIM testing |
| 07 | Interaction history and follow-up notes |
| 08 | Testing, optimization, and release preparation |

### Sprint 01 — Project Setup

- Create the Android project.
- Configure Kotlin and Jetpack Compose.
- Configure Material 3.
- Establish package organization.
- Configure dependency injection.
- Set up navigation foundations.
- Create the initial application theme.

### Sprint 02 — Design System

- Define typography.
- Define colors.
- Create reusable components.
- Implement light and dark themes.
- Build the main navigation structure.

### Sprint 03 — Local Database

- Configure Room.
- Define initial entities.
- Create DAOs.
- Implement database configuration.
- Establish repository contracts.

### Sprint 04 — Android Contacts

- Configure contact permissions.
- Read contacts through Android Contacts Provider.
- Implement contact selection.
- Map imported information into Callio models.

### Sprint 05 — Contact Management

- Create contacts.
- Edit contacts.
- Delete contacts.
- Search contacts.
- Display contact details.
- Manage favorites.

### Sprint 06 — Telephony

- Implement `ACTION_DIAL`.
- Test calling workflows.
- Evaluate Dual SIM behavior.
- Handle missing phone numbers and permissions.

### Sprint 07 — Customer Interactions

- Register interactions.
- Add notes.
- Associate interactions with contacts.
- Display interaction history.

### Sprint 08 — Stabilization

- Test application flows.
- Validate database migrations.
- Improve error handling.
- Optimize UI performance.
- Prepare a release build.

---

## Development Principles

Callio will follow a few principles throughout development:

**KISS**

Keep implementations simple and avoid unnecessary abstractions.

**SOLID**

Maintain clear responsibilities and boundaries between components.

**Separation of Concerns**

UI, business logic, and data access must remain independent.

**Offline-First**

The application must work without requiring a backend.

**Incremental Development**

Implement and validate features one step at a time.

**Maintainability**

Prefer readable, testable, and predictable code over premature optimization.

**Future Scalability**

Prepare reasonable architectural boundaries for future API integration without implementing SaaS complexity in the mobile MVP.

---

## Project Status

<sub>Initial planning and architecture definition.</sub>

- [x] Product vision defined.
- [x] Initial technology stack selected.
- [x] Architecture approach proposed.
- [x] Initial feature scope defined.
- [x] Offline-first strategy established.
- [ ] Android project initialized.
- [ ] Design system implemented.
- [ ] Local database implemented.
- [ ] Contact management implemented.
- [ ] Calling functionality implemented.
- [ ] MVP release.

---

## License

Callio Mobile is licensed under the **Apache License, Version 2.0**.

You are free to use, modify, and distribute this software in accordance with the terms of the license.

See the [LICENSE](LICENSE) file for the complete license text.

SPDX-License-Identifier: Apache-2.0
