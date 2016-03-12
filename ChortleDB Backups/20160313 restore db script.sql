USE [ChortleDB]
GO
/****** Object:  User [GianniC]    Script Date: 13/03/2016 12:56:06 AM ******/
CREATE USER [GianniC] FOR LOGIN [GianniC] WITH DEFAULT_SCHEMA=[dbo]
GO
/****** Object:  User [MitchellL]    Script Date: 13/03/2016 12:56:06 AM ******/
CREATE USER [MitchellL] FOR LOGIN [MitchellL] WITH DEFAULT_SCHEMA=[db_owner]
GO
ALTER ROLE [db_owner] ADD MEMBER [GianniC]
GO
ALTER ROLE [db_accessadmin] ADD MEMBER [GianniC]
GO
ALTER ROLE [db_securityadmin] ADD MEMBER [GianniC]
GO
ALTER ROLE [db_ddladmin] ADD MEMBER [GianniC]
GO
ALTER ROLE [db_backupoperator] ADD MEMBER [GianniC]
GO
ALTER ROLE [db_datareader] ADD MEMBER [GianniC]
GO
ALTER ROLE [db_datawriter] ADD MEMBER [GianniC]
GO
ALTER ROLE [db_owner] ADD MEMBER [MitchellL]
GO
ALTER ROLE [db_accessadmin] ADD MEMBER [MitchellL]
GO
ALTER ROLE [db_ddladmin] ADD MEMBER [MitchellL]
GO
ALTER ROLE [db_backupoperator] ADD MEMBER [MitchellL]
GO
ALTER ROLE [db_datareader] ADD MEMBER [MitchellL]
GO
ALTER ROLE [db_datawriter] ADD MEMBER [MitchellL]
GO
/****** Object:  Table [dbo].[Group]    Script Date: 13/03/2016 12:56:06 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Group](
	[ID] [int] IDENTITY(1,1) NOT NULL,
	[GroupName] [varchar](50) NOT NULL,
	[Description] [text] NULL,
 CONSTRAINT [PK_Group] PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[GroupAdmin]    Script Date: 13/03/2016 12:56:06 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[GroupAdmin](
	[GroupID] [int] NOT NULL,
	[AdminID] [int] NOT NULL
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[GroupOwner]    Script Date: 13/03/2016 12:56:06 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[GroupOwner](
	[GroupID] [int] NOT NULL,
	[OwnerID] [int] NOT NULL
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[GroupUser]    Script Date: 13/03/2016 12:56:06 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[GroupUser](
	[ID] [int] IDENTITY(1,1) NOT NULL,
	[GroupID] [int] NOT NULL,
	[UserID] [int] NOT NULL,
 CONSTRAINT [PK_GroupUser] PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[Subgroup]    Script Date: 13/03/2016 12:56:06 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Subgroup](
	[ID] [int] IDENTITY(1,1) NOT NULL,
	[ParentGroupID] [int] NOT NULL,
	[Name] [varchar](50) NOT NULL,
	[Description] [text] NULL,
 CONSTRAINT [PK_SubGroup] PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[SubgroupUser]    Script Date: 13/03/2016 12:56:06 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[SubgroupUser](
	[SubgroupID] [int] NOT NULL,
	[GroupUserID] [int] NOT NULL
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[TaskLog]    Script Date: 13/03/2016 12:56:06 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[TaskLog](
	[TaskTemplateID] [int] NOT NULL,
	[UserID] [int] NULL,
	[Assigned] [datetime] NOT NULL,
	[Deadline] [datetime] NOT NULL,
	[StatusChanged] [datetime] NULL,
	[Comment] [text] NULL,
	[Status] [tinyint] NOT NULL
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]

GO
/****** Object:  Table [dbo].[TaskStatus]    Script Date: 13/03/2016 12:56:06 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[TaskStatus](
	[ID] [tinyint] NOT NULL,
	[Status] [varchar](50) NOT NULL,
	[Description] [text] NULL,
 CONSTRAINT [PK_TaskStatus] PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[TaskSubGroupAssignment]    Script Date: 13/03/2016 12:56:06 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[TaskSubGroupAssignment](
	[SubGroupID] [int] NOT NULL,
	[TaskTemplateID] [int] NOT NULL
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[TaskTemplate]    Script Date: 13/03/2016 12:56:06 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[TaskTemplate](
	[ID] [int] IDENTITY(1,1) NOT NULL,
	[GroupID] [int] NULL,
	[TaskName] [varchar](50) NOT NULL,
	[TaskDescription] [text] NULL,
	[StartDate] [datetime] NOT NULL,
	[Frequency] [int] NULL,
	[Repetitions] [tinyint] NULL,
	[Delay] [int] NULL,
	[EstimatedDuration] [smallint] NULL,
	[EstimatedDifficulty] [tinyint] NULL,
	[Status] [tinyint] NULL,
 CONSTRAINT [PK_Task] PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[TaskUserAssignment]    Script Date: 13/03/2016 12:56:06 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[TaskUserAssignment](
	[UserID] [int] NOT NULL,
	[TaskTemplateID] [int] NOT NULL
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[User]    Script Date: 13/03/2016 12:56:06 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[User](
	[ID] [int] IDENTITY(1,1) NOT NULL,
	[Username] [varchar](50) NOT NULL,
	[FirstName] [varchar](50) NULL,
	[LastName] [varchar](50) NULL,
	[Email] [varchar](128) NOT NULL,
	[PasswordHash] [char](64) NOT NULL,
 CONSTRAINT [PK_User] PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY],
 CONSTRAINT [Unique_Email] UNIQUE NONCLUSTERED 
(
	[Email] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY],
 CONSTRAINT [Unique_Username] UNIQUE NONCLUSTERED 
(
	[Username] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[UserFriend]    Script Date: 13/03/2016 12:56:06 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[UserFriend](
	[UserID] [int] NOT NULL,
	[FriendID] [int] NOT NULL
) ON [PRIMARY]

GO
ALTER TABLE [dbo].[TaskLog] ADD  CONSTRAINT [DF_TaskLog_EntryDate]  DEFAULT (getdate()) FOR [Assigned]
GO
ALTER TABLE [dbo].[GroupAdmin]  WITH CHECK ADD  CONSTRAINT [FK_GroupAdmin_GroupUser] FOREIGN KEY([GroupID], [AdminID])
REFERENCES [dbo].[GroupUser] ([GroupID], [UserID])
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[GroupAdmin] CHECK CONSTRAINT [FK_GroupAdmin_GroupUser]
GO
ALTER TABLE [dbo].[GroupOwner]  WITH CHECK ADD  CONSTRAINT [FK_GroupOwner_GroupAdmin] FOREIGN KEY([GroupID], [OwnerID])
REFERENCES [dbo].[GroupAdmin] ([GroupID], [AdminID])
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[GroupOwner] CHECK CONSTRAINT [FK_GroupOwner_GroupAdmin]
GO
ALTER TABLE [dbo].[GroupUser]  WITH CHECK ADD  CONSTRAINT [FK_GroupUser_Group] FOREIGN KEY([GroupID])
REFERENCES [dbo].[Group] ([ID])
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[GroupUser] CHECK CONSTRAINT [FK_GroupUser_Group]
GO
ALTER TABLE [dbo].[GroupUser]  WITH CHECK ADD  CONSTRAINT [FK_GroupUser_User] FOREIGN KEY([UserID])
REFERENCES [dbo].[User] ([ID])
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[GroupUser] CHECK CONSTRAINT [FK_GroupUser_User]
GO
ALTER TABLE [dbo].[Subgroup]  WITH CHECK ADD  CONSTRAINT [FK_SubGroup_Group] FOREIGN KEY([ParentGroupID])
REFERENCES [dbo].[Group] ([ID])
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[Subgroup] CHECK CONSTRAINT [FK_SubGroup_Group]
GO
ALTER TABLE [dbo].[SubgroupUser]  WITH CHECK ADD  CONSTRAINT [FK_SubgroupUser_GroupUser] FOREIGN KEY([GroupUserID])
REFERENCES [dbo].[GroupUser] ([ID])
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[SubgroupUser] CHECK CONSTRAINT [FK_SubgroupUser_GroupUser]
GO
ALTER TABLE [dbo].[SubgroupUser]  WITH CHECK ADD  CONSTRAINT [FK_SubgroupUser_Subgroup] FOREIGN KEY([SubgroupID])
REFERENCES [dbo].[Subgroup] ([ID])
GO
ALTER TABLE [dbo].[SubgroupUser] CHECK CONSTRAINT [FK_SubgroupUser_Subgroup]
GO
ALTER TABLE [dbo].[TaskLog]  WITH NOCHECK ADD  CONSTRAINT [FK_TaskLog_TaskStatus] FOREIGN KEY([Status])
REFERENCES [dbo].[TaskStatus] ([ID])
GO
ALTER TABLE [dbo].[TaskLog] CHECK CONSTRAINT [FK_TaskLog_TaskStatus]
GO
ALTER TABLE [dbo].[TaskLog]  WITH CHECK ADD  CONSTRAINT [FK_TaskLog_TaskTemplate] FOREIGN KEY([TaskTemplateID])
REFERENCES [dbo].[TaskTemplate] ([ID])
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[TaskLog] CHECK CONSTRAINT [FK_TaskLog_TaskTemplate]
GO
ALTER TABLE [dbo].[TaskLog]  WITH CHECK ADD  CONSTRAINT [FK_TaskLog_User] FOREIGN KEY([UserID])
REFERENCES [dbo].[User] ([ID])
ON DELETE SET NULL
GO
ALTER TABLE [dbo].[TaskLog] CHECK CONSTRAINT [FK_TaskLog_User]
GO
ALTER TABLE [dbo].[TaskSubGroupAssignment]  WITH CHECK ADD  CONSTRAINT [FK_TaskSubGroupAssignment_SubGroup] FOREIGN KEY([SubGroupID])
REFERENCES [dbo].[Subgroup] ([ID])
GO
ALTER TABLE [dbo].[TaskSubGroupAssignment] CHECK CONSTRAINT [FK_TaskSubGroupAssignment_SubGroup]
GO
ALTER TABLE [dbo].[TaskSubGroupAssignment]  WITH CHECK ADD  CONSTRAINT [FK_TaskSubGroupAssignment_TaskTemplate] FOREIGN KEY([TaskTemplateID])
REFERENCES [dbo].[TaskTemplate] ([ID])
GO
ALTER TABLE [dbo].[TaskSubGroupAssignment] CHECK CONSTRAINT [FK_TaskSubGroupAssignment_TaskTemplate]
GO
ALTER TABLE [dbo].[TaskTemplate]  WITH CHECK ADD  CONSTRAINT [FK_TaskTemplate_Group] FOREIGN KEY([GroupID])
REFERENCES [dbo].[Group] ([ID])
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[TaskTemplate] CHECK CONSTRAINT [FK_TaskTemplate_Group]
GO
ALTER TABLE [dbo].[TaskUserAssignment]  WITH CHECK ADD  CONSTRAINT [FK_TaskUserAssignment_TaskTemplate] FOREIGN KEY([TaskTemplateID])
REFERENCES [dbo].[TaskTemplate] ([ID])
GO
ALTER TABLE [dbo].[TaskUserAssignment] CHECK CONSTRAINT [FK_TaskUserAssignment_TaskTemplate]
GO
ALTER TABLE [dbo].[TaskUserAssignment]  WITH CHECK ADD  CONSTRAINT [FK_TaskUserAssignment_User] FOREIGN KEY([UserID])
REFERENCES [dbo].[User] ([ID])
GO
ALTER TABLE [dbo].[TaskUserAssignment] CHECK CONSTRAINT [FK_TaskUserAssignment_User]
GO
ALTER TABLE [dbo].[UserFriend]  WITH CHECK ADD  CONSTRAINT [FK_UserFriend_User] FOREIGN KEY([UserID])
REFERENCES [dbo].[User] ([ID])
GO
ALTER TABLE [dbo].[UserFriend] CHECK CONSTRAINT [FK_UserFriend_User]
GO
ALTER TABLE [dbo].[UserFriend]  WITH CHECK ADD  CONSTRAINT [FK_UserFriend_User1] FOREIGN KEY([FriendID])
REFERENCES [dbo].[User] ([ID])
GO
ALTER TABLE [dbo].[UserFriend] CHECK CONSTRAINT [FK_UserFriend_User1]
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'GroupUsers must belong to an existing group' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'GroupUser', @level2type=N'CONSTRAINT',@level2name=N'FK_GroupUser_Group'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'A group within a roup that may be allocated specific tasks' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'Subgroup'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Subgroup must be a child of existing group' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'Subgroup', @level2type=N'CONSTRAINT',@level2name=N'FK_SubGroup_Group'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Links a user and a subgroup' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'SubgroupUser'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Log of all status changes for pending tasks. Current time inserted by default.' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'TaskLog'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Assign subgroups to tasks' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'TaskSubGroupAssignment'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Task template must belong to an existing group' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'TaskTemplate', @level2type=N'CONSTRAINT',@level2name=N'FK_TaskTemplate_Group'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Table containing all data pertaining to application users' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'User'
GO
