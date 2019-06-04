# TarotHelper
An android app to help new practitioners learn how to read Tarot cards 

created by The Voice and The Breath

What follows is the most uptodate design doc available
4th June 2019

PROJECT OVERVIEW
Tarot Helper will be an Android app meant to help everyday people learn how to read Tarot cards. The primary focus is on the more rote-memory tasks involved in the process, such as memorizing card meanings, although annotating other, more tuition based aspects of the practice will also be included.
The app will primarily be broken into three parts. Part 1 is a Tarot Journal, where users can keep notes on aspects of their practice, like card-a-day readings, notable spreads, practice preferences and invocations, etc. The journal will be divided into sections that reflect these topics, and include appropriate templates/wizards for adding entries to each topic. 
Part 2 is a set of meanings and definitions that the user can review and alter and update these meanings as they want to reflect their own understanding and practice of the cards. This part of the app will be divided into sections that will overlap and mirror the Journal part of the app, including things like spreads, card meanings, symbols and astrology, elemental correlations, etc.
Part 3 is to design and implement quizzes and mini-games to assist in rote-memory. These games will default to use the out-of-the box meanings and definitions included in part 2, but will update to reflect any changes that users make to this section. Each of these mini-games will have their own design doc as they will be mini-projects in their own right.
Finally, as hinted at in part 3, there will be an interconnectedness aspect of the project. Changes in the dictionary/meanings section will proliferate to the mini-games and quizzes section of the project, and also be reflected in the journal wizards and templates. Journal entries will allow tags, that will generate links from section 2 to reflections in section 1. Also, within the journal section, the tags will link entries to one another, and certain sections will inform the others. For instance, each spread that a user adds or customizes in the Spreads section will be an option to populate in the Readings section of the Journal.
The project will be implemented in stages. The plan is to implement part 2 first, them move onto part 1, and then finally shift gears to part 3. In this way, the app will have the greatest chance of being useful in a partially completed state, should constraints prevent taking the project all the way through to completion. 
GOALS
This is a personal project. The primary goal is to develop an App Store ready project from start to finish and go through all the steps and processes to see the app through to completion.
As a secondary goal, learning to develop good habits and discipline surrounding the “Clean Code” and “Test-Driven-Development” ideologies is of prime importance. The hope is that learning about and developing a practice around these strategies will help me in any possible software development aspects of my future.
Finally, learning about and implementing appropriate software architecture(s), design patterns, and learning how to document a project design will play a major part in deciding my project-related activities outside of simply generating code.
SPECIFICATIONS
This app will be developed in iterations, with each iteration being a releasable project. An iteration is not to be considered complete unless it has a robust suite of tests, is designed using the chosen architecture strategy, and, of course, fully implements the specification for the iteration. The inclusion of different layouts for device orientation issues and activity/fragment life-cycles is a given.
There are three sets of milestones, one for each major section of the project as outlined above. The goal for the length of the development part of a given iteration is approximately 2-3 weeks. This estimate will be adjusted as the project gets developed and work cycles projects are established and become more accurate.
EPOCH 1 -  Basic “Encyclopedia” functionality 
Milestone 1 - Viewing of out-of-the-box card images, meanings, and keywords
This first milestone is to get a bare minimum, working version of the project up and running. This version will simply allow users to view card meanings and keywords for each of the 78 cards in both their upright and reversed positions. The original set of meanings will not be final, and are expected to be adjusted over the length of the project.
This milestone has a single viewpager that cycles through the cards individually and displays  meanings will be displayed for a particular card in it’s given orientation.
Milestone 2 - Add sort and shuffle options activity/menu for the cards
Add an activity or a menu that gives different options to choose which cards to display, in what order, and whether or not to include reversals.
Milestone 3 - Expand the definitions section to include additional categories 
The additional sections are the other planned definition/meaning sections: Symbols, Colors, Suit, Number, Arcana, Mode, etc. The content in the database for each of these sections does not need to be fully fleshed out at the end of this milestone, but the sections should be there and setup to work with any content that may be added to the database over time. This milestone involves adding a new parent activity to select which section the user wishes to navigate to.
Milestone 4 - Add sort options for the other categories in the section
Add a sort options menu and/or shared preference to the menu so that the order in which menu options are displayed can be changed. 
Milestone 5 - Flesh out definitions/meanings in each section
This is where the default, out-of-the-box definitions and meanings of each section already implemented will be added to the database. This milestone is all about the database and assets; there shouldn’t need to be much modification to the codebase, even though there will probably be bug-fixes. During this time, there will also be a need to identify a body of appropriately sized and cropped images/ thumbnails for display along with the additional content.
Milestone 5 - Polish
This milestone is all about presentation. Adjusting styles, fonts, primary/accent colors, and material design are the focus as this first Epoch is closed out. Animations and transitions are also up for grabs, where deemed appropriate. At the end of this milestone, the app should be ready for release to the app store.
EPOCH 2 - User updated encyclopedia entries
Milestone 1 - User updated meanings, and keywords
Milestone 6 will allow users to provide their own meaning for cards, symbols, categories, etc. The focus here is on UI. There needs to be a smooth way to navigate to the edit meaning screen(s) without it happening all the time by accident. Perhaps a menu item with a new activity or two is in order.
Milestone 2 - Restoring default, out-of-the-box meanings
When a user assigns their own meaning or definition to a card or category, the original meaning should be restorable. This can be done at the individual category level, the card level, or globally. The classes, databases, menus, and activities all need to be updated (and some added) to reflect this functionality.
Milestone 3 - Adding new entries to certain categories
In particular, the colors and symbols categories should have a way to easily add a category. This may involve several screens and an implicit intent to get a photo. Also, a color picker.
Milestone 4 - Polish
I.e. style, material design, transitions, and animations
EPOCH 3 -  The Spread Design Tool
Milestone 1 - Create an initial version of the SDT
This initial version of the SDT should include all the basic functionality. This means that the user should be able to add cards, move them around the screen, select previously added cards, delete cards, and assign representations to each of the card positions. Rotations will be included. 
For this milestone, the tool will be a separate program from the app. Also, spreads will not be persisted at this point. This is simply about creating user defined spreads.
Milestone 2 - Add the SDT to the project
This is where more of the structure of the app will be developed. A new parent activity that allows the user to navigate between the “Encyclopedia” and the SDT needs to be created. This activity needs to allow for additional sections of the app to be added later (i.e., the journal and game/quiz sections). 
A user navigation paradigm needs to be established at this point. What will the cue from the user be for creating a spread? What will be the underlying navigation conventions that unify the various sections of the app? These questions need to be explored and some decisions need to be made by the end of this milestone.
Milestone 3 - Spread persistence 
What good is generating a spread for the user if it is not subsequently persisted and accessible at a later point? A serializable encoding scheme for saving a spread’s state to the database and reconstructing it later from that encoding will need to be established and implemented. Also, a way for the user to request a save and a view for displaying saved spread needs to be added to the application. 
Milestone 4 - Out-of-the-box spreads and spread groupings
At this milestone, several common spreads will be added to the program. Which spreads to include will need to be decided on, though the list can be added to or finalized later. There also needs to be a way to group spreads and, if desired, to trash and archive spreads that the user isn’t interested in.
Milestone 5 - Spread size/orientation
Some spreads only use 1-3 cards, some use more. It would be nice if the size of the cards on the screen adjusted to match the size and complexity of the spread, once the spread is saved. Also, some spreads are laid out more vertically than horizontally. Accordingly, the desired layout orientation should be adjustable by the user.
At this point in time, should there also be a preview spread screen so that users can see how the spread will look on the screen once adjustments are made? If so, this would be a good time to generate such a screen.
Milestone 6 - Polish
A lot has changed in this milestone. It’s time to review the style, colors, and material design decisions that were made at the end of the last epoch. Special consideration should be paid to the look and feel of the SDT and how it fits in to the rest of the app.
EPOCH 3 - Readings - From own cards
EPOCH 4 - Readings - From within app


