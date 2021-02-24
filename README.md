## 技术介绍
以太坊作为区块链2.0技术，区别于以比特币为代表的区块链1.0技术，日益成为焦点。同时java提供了强大的第三方库web3j以供区块链技术的开发，搭配springboot框架，使得以太坊开发具有较高天花板。

## 项目介绍
整体流程图如下
![](http://xhades.top/blog/wp-content/uploads/2021/02/MUJ4ERU58H2F7BQ6HLY9.png)

&emsp;&emsp;其中前端最后选择了较为简便的html + js + thymeleaf(配合springboot的模板技术) 开发，其中js主要使用jquery完成 

&emsp;&emsp;本小组总共四人，其中鹏哥作为队长（很重要！！）参与了大部分的技术开发，solidity需求的提出以及前后端关键部分的完善。严小幸和睿霖完成solidity合约部分的编写。本人参与前后端的部分开发（40%不到的工作量？），同时且由于本人`SSM + springboot`框架知识体系不够完善和jQuery的生疏，花费了相当多的时间进行测试。前前后后总共历时五天左右完成。

## 成果介绍
### /index页面
展示多轮投票结果，并操纵终止投票选出获胜者
![](http://xhades.top/blog/wp-content/uploads/2021/02/index.png)

### /addBollot页面
由发起人添加投票，其中包含了jQuery实现的各项提交查验操作
![](http://xhades.top/blog/wp-content/uploads/2021/02/addBallot.png)

### /startVote页面
此页面用来给投票者进行投票
![](http://xhades.top/blog/wp-content/uploads/2021/02/votingPage.png)

### 数据持久化
数据库构造如下
![](http://xhades.top/blog/wp-content/uploads/2021/02/database.png)
