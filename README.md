Codefest 2015 team G repository
===========

# The team G - 4.PINS


TEAM MEMBER | EXO TEAM
------------ | -------------
Trần Thị Thùy Dương | ADM
Lê Thị Thu Hà | UXP
Nguyễn Thị Lan | DOC
Đặng Thị Mây | UXP

# How to build

	git clone git@github.com:exo-codefest/2015-team-G.git
	cd 2015-team-G
	mvn clean install

# Overview 
<img src="images/generalmindmap.png" />
* Name: PinMind Add-on

* Purposes:  Facilitate brainstorming process between team members and allow voting to select great ideas effectively and efficiently

* Preface: Due to the limitations of human resource and time, our add-on only stops at basic functions, the potential perspectives will be provided for future improvements. Our team are happy to receive your comments and suggestions, so please feel free to give us some feedback!
* Acknowledgement: We are grateful to Kenneth Kufluk (https://github.com/kennethkufluk) for providing his free js-mindmap library under the repository: https://github.com/kennethkufluk/js-mindmap. We have modified the original source code to fit in eXo Platform.

# Definitions:

- A team can work on many debating topics
- A topic should be discussed between members to collect ideas
- Each idea should be evaluated by all members
- A topic can have one among the following status: Debating, Upcoming, Closed
- An idea can have a special status as “hot idea” if it is called for voting by the administrator

# Done features:

- Creating new topic
- Adding sub ideas
- Removing sub ideas
- Creating mind map  to work on each topic
- Editing topics and ideas 
- Following hot ideas 

# How to use:

**Step 1**. Create a new space such as “Team G”, then, the add-on PinMind will be automatically added to this space on the navigation bar. Click on the PinMind symbol, you will see that topics are categorized by their status as follows:

<img src="images/navigationbar.png" />

**Step 2**. Click <img src="images/newtopicbtn.png" /> to add a new topic:

 <img src="images/newtopicform.png" />
 
Fill essential information in this form and click Add button, the new topic will be displayed.
 
**Step 3**. Click on the topic you want to edit, you will be redirected to the diagram screen with the root node only:

<img src="images/rootnode.png" />

**Step 4**. Right-click on the root node to access menu bar:

<img src="images/menubar.png" />

**Step 5**. Select Add node to add new sub ideas:

<img src="images/nodedemo.png" />

or Edit node to edit the node content:

<img src="images/nodeedit.png" />

Press Enter key to accept changes.
Or Remove node to delete the selected node. This node and its children will be deleted.

**Step 6**. Besides, you can click on any node to move focusing on this node , its parent as well as its children

<img src="images/focusnode.png" />

In addition, this add-on provides "hot ideas" function to allow keeping up with the debating topics.

<img src="images/top3-1.png" />

# Perspectives:

- Voting: allow members to vote for all ideas
- Graphics design: provide more responsive and attractive design to increase user experience
- Notification: enable sending notifications to members when the administrator makes a call for voting
- Import/export ideas tree: quickly import and export the ideas tree under a specific data format
- Real-time review: allow members to comment on each idea and the administrator can accept or ignore them
