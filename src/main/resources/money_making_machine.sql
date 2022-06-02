-- MySQL dump 10.13  Distrib 5.7.15, for Linux (x86_64)
--
-- Host: localhost    Database: money_making_machine
-- ------------------------------------------------------
-- Server version	5.7.15

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `act_evt_log`
--

DROP TABLE IF EXISTS `act_evt_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `act_evt_log` (
  `LOG_NR_` bigint(20) NOT NULL AUTO_INCREMENT,
  `TYPE_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `PROC_DEF_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `PROC_INST_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `EXECUTION_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `TASK_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `TIME_STAMP_` timestamp(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3),
  `USER_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `DATA_` longblob,
  `LOCK_OWNER_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `LOCK_TIME_` timestamp(3) NULL DEFAULT NULL,
  `IS_PROCESSED_` tinyint(4) DEFAULT '0',
  PRIMARY KEY (`LOG_NR_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `act_evt_log`
--

LOCK TABLES `act_evt_log` WRITE;
/*!40000 ALTER TABLE `act_evt_log` DISABLE KEYS */;
/*!40000 ALTER TABLE `act_evt_log` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `act_ge_bytearray`
--

DROP TABLE IF EXISTS `act_ge_bytearray`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `act_ge_bytearray` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `REV_` int(11) DEFAULT NULL,
  `NAME_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `DEPLOYMENT_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `BYTES_` longblob,
  `GENERATED_` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`ID_`),
  KEY `ACT_FK_BYTEARR_DEPL` (`DEPLOYMENT_ID_`),
  CONSTRAINT `ACT_FK_BYTEARR_DEPL` FOREIGN KEY (`DEPLOYMENT_ID_`) REFERENCES `act_re_deployment` (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `act_ge_bytearray`
--

LOCK TABLES `act_ge_bytearray` WRITE;
/*!40000 ALTER TABLE `act_ge_bytearray` DISABLE KEYS */;
INSERT INTO `act_ge_bytearray` VALUES ('10',1,'ËØ∑ÂÅáÊµÅÁ®ã.process_5.png','8','âPNG\r\n\Z\n\0\0\0\rIHDR\0\0û\0\0\0\Ê\0\0\0|òÖ\0\0˝IDATx^\Ì\›[å]uΩ-¢∆Ñå\◊\ËÉ\—gç$í¯`0j|ÚÉml\“\ (\"A®∂\‘ML|\0%Òv†\—Q\‘Ùà\ËE)-DN¢‹ä@z9\n⁄´îPhµùYg˝÷ô=\ÓÆ=-√¥3˝ˇf>\…7\”\Ÿ{Ìµß3ø¸˜wØ}\Ît\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0`¿UUu\⁄÷≠[ˇˆ¿Tk◊Æ≠\ÓΩ˜^ô\·\ƒ\Ô}√Ü\r#\Î÷≠˚N˚ÔìÅ:˘\…>C\0à(˜\›w_µs\Á\Œ\Í¿Å’ø˛ı/ô\·\ƒ\Ô=~ˇ\Î◊Ø≠Kƒíˆﬂ®tf\Ë\‰\'˚0 \‚(U\‹`µo\»d\Ê≥c«é(\r˚\€£“ô°ríuÜ\0Òù£Te$˛Ò∞i˚oT:3TN≤\Œ\0\"n§\⁄7^rÚí±4ò°≤íqÜ\0ì-\r˚˜˝£\⁄˙\–UèØπ∂I¸;Nko\'«óå•¡ïïå3¿ÄòLix\·πg´\«\Ó^\\=|\Á\ÂG$NãÛ\⁄\€\À‘ì±4ò°≤íqÜ\0ì)\r{|U_a\Ë\Êô\«ˇªo{ôz2ñ3TV2\Œ\0b2•\·âı\À˚\ B7q^{{ôz2ñ3TV2\Œ\0b2•\·±{óÙïÖn\‚ºˆˆ2ıd,\rf®¨dú!\0Ñ\“PV2ñ3TV2\Œ\0b2•!^Å\‹.\›\ƒy\Ì\Ìe\Í\…X\Z\ÃPY\…8C\0à…îÜß7\ﬁ\ÿW∫âÛ\⁄\€\À‘ì±4ò°≤íqÜ\0ì)\r{ü}§z\ÏûoıÜ8-\Œko/SO\∆\“`Ü\ J\∆`@L¶4D∂˝œäæ\“ßµ∑ì\„K\∆\“`Ü\ J\∆`@L™4<X=ΩÈ¶æ\“ß\≈y}\€Àîì±4ò°≤íqÜjÒ3ü®\0P™ó+\rÒ©2Omº°Ø0t\Á˘\‰ôóå•¡ïïå3\‘\È/è\«\0Ju\‘\“p`ıèß\◊UèÆæ™Ø(¥\€ƒ∂é\\2ñ3TV2\ŒPGiïÜó;Bu¥8ru¸\…X\Z\ÃPY\…8C\≈`0LT\Z&sÑ\ÍhâÀ∂˜\'ìO\∆\“`Ü\ J\∆\Í(û\0Éa¢\“\–.Ø4\Ì˝\…‰ì±4ò°≤íqÜ:ä\'¿`ò®4\»\…K\∆\“`Ü\ J\∆\Í(û\0ÉAi(+KÉ*+g®£x•°¨d,\rf®¨dú°é\‚	0îÜ≤í±4ò°≤íqÜ:ä\'0\€UUu\Í∆çóØX±bœí%KFÆºÚ\ \ÍÇ.®\ÊÕõW}\ÈK_™.ª\Ï≤\—˙¥/[∂Ï°ÖûŸæ¸l°4îïå•¡ïïå3‘ôDÒ¸\Ïg?{FΩ>Øú;w\Óé˙\Î˛:ácΩ˚∫\ÏÙï±]˚≤\0\'M]8O_Ωzıã/ÆÜáá´¸\‡’É>XmŸ≤•⁄ªwo\‚k|ß\«˘\Áùw^uÒ\≈™∑ø™ΩøÏîÜ≤í±4ò°≤íqÜ:\«(ûs\ÊÃπ¥.î˚\∆J\Êd≥/.\◊\ﬁ¿åz\‡Ån_∂lYu\…%óTıø´ëëë¶hæú\ÿ.∂Ø\Àgu\·ÖæT/hn\Ô;+•°¨d,\rf®¨dú°\Œ\≈3\÷Ÿ∫@Óû†Tæí\ÏûM\Î5êD\›Oπ˚Óª∑ù˛˘\’\Ô~˜ª\Í\·\√\Ìn9)qπ∏¸ÇbAª≤}=)\re%ci0Ce%\„uZ≈≥.ãW\r\rçˆñ\»˘Û\ÁW\◊]w]µv\Ì\⁄	°ä\”\„¸ÿÆU>Gc=\◊0}\Íu\Èî;\Ó∏\„Öx˛\Ê_˛Úó#õ\‰\≈~\‚a˙zaºπ}}\Ÿ(\re%ci0Ce%\„uzägΩÆ˛¥\Œxq<˜\‹s´˝\ËG\’s\œ=\◊^ä\'\€\≈ˆqπ\ﬁ\Z˚m]\'¿âG:\„\≈B;w\ÓlØO\«%ˆ∑`¡ÇÙ˜§ïÜ≤í±4ò°≤íqÜ:c\≈s\ÏH\ÁxY\\¥hQµm€∂ˆÚ;)qπ∏|o˘Ãæ^Ö€¥i\”\ÌQ:O‘ëŒ∂\ÿ\Ô¸˘ÛG\œ>˚Ïè¥Ø;•°¨d,\rf®¨dú°\ŒXÒ\Ï}x˝Í´ØÆû˛˘ˆ≤˚ä\ƒ\Âc?Ωªg^ØÅÇ\’k\Œ\È\ﬂ˛ˆ∑õ\ÁdNßU´Vçûs\Œ9ª\€◊üÖ\“PV2ñ3TV2\ŒPg¨xˆ\È<\ﬁ\“\Ÿ˚i˘\‹’ærÄ\„∂zı\Í\ÕÒ\Íı©æêh≤bˇ\√\√\√#Yﬂ∫Ci(+KÉ*+g®\”S<„πôS}x˝hbΩ\œ˘Ã∫^Ö™◊ôSØπ\Êö\Ê-êf\¬˝˜\ﬂ=\ﬂ˛92P\Z\ J\∆\“`Ü\ J\∆\ÍÙ\œxa\–tà˝ˆıLπ^Ö™\Áı_¯\¬&˝>ù\«+Æßæ7=Z\“\'f\r\r=W\Áíˆ\ÈmJCY)©4ò°ú\…6Cc\ÎfS<„≠ê&˚\ÍıW*ˆ\€˚VK%≠\◊@r∑\‹r\À\ﬁ¯ƒ°ô¥t\È“És\ÁŒΩµ˝≥ú,\›\≈ı\Â~•°¨îT\Z\ÃP\Œdõ°±u≥)û\◊_}{i=°bˇ›ü©§ı\ZHnŸ≤e#3ı0{WΩ\ÿ¨≥g\€?\À\…\“Ûê\“1~•°¨îX\Z\ÃPÆdõ°±u≥)ûk÷¨i/≠\'T\Ïø\Á\Á)fΩíª\‚ä+™≠[∑∂◊úiı\‰ìO™≤˝\Ìü\Âdi/¯G[¯ïÜ≤Rri0C9ímÜ\Ê˝ˇ∫\Ÿ\œ\È^∑cˇ=?G1\Î5ê\\ºw\Á?ˇ˘\œˆö3≠ˆ\Ó\›/0:\‹˛YNñˆB\ﬂNw\·W\Z\ JÜ\“0f\Ë±\«\Î;≠õx˚ùGy§:p\‡¿¯iÒ\Ôc]¶§¨X±\"˛>ﬂ™s\÷\ÿ\ﬂÒ¨ìı˝º	\Ê¶71Cı\◊—∑º\Â-MÒú\Óu;ˆ\ﬂs˝≈¨\◊@rÒÚCáµ◊úiU_\ﬂü+ú%•óÜxì˛x\⁄D;õ6m™÷≠[◊º`†}ôK/Ω¥˙\·\ÿwzÜ¥ˇ>R˙µÛ\Î_ˇz¸a\›ˆyëáz®9◊Æ]\„ß˝ˆ∑ø≠^˝\ÍW\«#}€óñˆ\ﬂ\'C\ﬁÛû˜4øÛ\È^∑cˇΩ\◊€∫\È\0òöÛ\œ?\⁄\Ô9∑\ÌﬁΩ;j/\Êt{a\Ô&\€√§ü˘\Ãgö§£\Â¡\Ïª\Ã\Ÿgü›úG~\⁄ÁïûG<≥\Õ\–˙ı\Î˚\Êf¢\ƒöÿæ∑xŒô3ßoª\»\Á?ˇ˘æ\Î)%éx\÷Ûs†˛z\„\ÿˆ#éx©}ı´_ù\Ó\Á\nµmﬁº˘\≈y=ghÇÖæ\ÔI˝°Ù“∞gœû\ÍO˙S_n∫\È¶\ÊÜ*˛›æ\ÃK/Ω‘î\œ◊ø˛ı\’SO=\’w~\…)πxfù°nÒ\\πreµzı\Í\Íé;Ó®ñ/_\ﬁ˘åÔª≥\≈ÛÚ\À/?¢`∆∂Ò\ÈgØ}\Ìk´ÎÆª.ﬁ≥∑\Ÿ\ÓÈßüÓªûRímÜ\∆\÷\Õ\Ê˜=\›\Î∂\Áx\”\‚\ﬂ¯∆øg˙U\ÌıçÿéyΩJÚX}Ø\“K\√/˘Àæ£MΩâíˆe\"ã/n\Œˇ\‰\'?\Ÿw^\…)±4dü°}˚ˆUøˇ˝\Ô´\'ûx¢)åø¯\≈/öŸ∏Û\Œ;õ\Ô}Ù\—Ê¥ù;w6\ﬂÛõ\ﬂl\Œˇ\Ÿ\œ~\÷\‹Ò9\ÔºÛ™˜æ˜Ω’ã/æXùu\÷Y’µ\◊^\€w%%\€ç≠õ\Õ\Ô‹´⁄Åîñ.]˙\–Løè\Á¢EãvïÙæp\«Z\Ë{ï\\\Zˆ\Ó\›€îÖâG¨\Íø˙\„ˇ\ÿl◊æ\Ï˚\ﬁ˜æ\Í\Õo~s≥Õ™U´˙\Œ/5%ïÜ\Ÿ0C\›\\p¡}wZz\Áw∑]∏pas⁄ß>ı©\Í\Î_ˇzÛ\ÔSO=µz’´^UΩ\Ìmo´ûyÊôæ˝óîl3\‰}<ÅÙ\Íé3ááágÚìãF\ÍÖl$\„\'aî\\\Z\‚àSgÇí\–\Œ\Õ7\ﬂ|\ƒ\Â\∆nxõ£•ˇ¯«´wΩ\Î]\ÕQØˆ˛KLI•a≤Jû°n¢X\∆,¸˝\Ôo^(Tˇ\ÿÕã\”\‚˚~É\„\≈3\Ê\‰MozSs˛;\ﬁÒé\Í∂\€n´\Óπ\Áû\Êπ∆ßúrJu˚\Ì∑Wü˛Ùß´ç7ˆ]G)\…6C3˘\…Eü˚\‹\Á∆ãg\∆ı\Z(\ÿE]th¶nØoú∂\ŒK˙Ÿø•óÜ[oΩµ˙\ÿ\«>V=¸\√’Ü\rößxÆ\ﬁˆ\Ì€´3\œ<≥∫Ò\∆è\ÿ>˝¿>Pùq\∆\Õ˜˛ÛüõW#\«{ª∂˜]b≤ïÜP˙E&{\ƒÛ{\ﬂ˚\ﬁxÒºÎÆªöwUà\¬˙ö◊º¶˙\…O~\“\r=˝Ù”è˙èíqÜ:cá(Ñ>´Hixx¯™ã/æ∏:|¯p{˝9°\Í˝\Z\Z˙˜ú9s.mˇî^\Z\‚a\Õ8\¬Ù\Ówø{¸\Ë\ﬂ¯\∆\Í\Ìo{ı°}®⁄≤e\À\€w&ç\Á\ÔuOã˜uçáI\€GFKL\∆\“P˙E¢Xvü\Ô€Ω\”}aZ\ÃQ∑xŒù;∑˙\‚øÿúØjè;,1{Ò\Ï>o¯ñ[n\È\€I\…8Cùû\‚y\Óπ\ÁV€∂mk/µ\«%ˆw\Œ9\Áåœ¨\Î5P∏EãΩØHùNÀó/∫^\»vµØ;ã\“KCº\‡\„˚\ﬂˇ~s\„\ﬂªq˙\ËG?\⁄≈å\Áku\ﬂ\«ÛØ˝kıâO|¢9Ω}4\ﬁ¸˝\ÔS>ø˚\›\Ôˆ]GI\…X\ZJü°\»dèx˛\·|œòù(-›∑\Ëä˙˘\œﬁ∑ˇííqÜ:=\≈3RØ\›\Õ\Ô˛Dà˝\\x·ÖΩG;”Æ\◊@\·\Íãè,X∞`4.õıç‘ñ°°°ë∏ûˆugQriàΩ\ÓuØ´\ﬁÜ7T_˘\ W™ˇ¯\«ÕçSºE\“eó]÷î\ÃxXÙæ˚\Ó´÷Æ][Ωı≠omû\◊\Ÿ\ﬁO\‰\Ÿgüm≤èùµ\œ+)KC\…3\‘\Õ\r7\‹P}\Ìk_k^¡\ﬁ=r˛´_˝™˘>^@£w∑Ìæègºà-\Ê,\Ê\Ô¥\”Nk>î\‡\À_˛r3wÒ*¯ˆuîíå3\‘˘OÒˇ é´Øæ˙∏\Àg\\˛™´Æ\Í-ù£ô\◊k Åz°π2\ﬁ%éúùH€∂mã∑O\Zô3g\Œ\Ì\ÎÃ§Ù\“E2éjv˛|\Á;\ﬂYΩ\¬\Õyõ7oÆÆπ\Êö\Ê};\„˚\Ó\ÈG\À¡É´˝˚˜˜ù^R2ñÜ\“g®ùˆC\Ì\Ìtãg<ç#Ê≠æÛ:˛âE1CÒêm\ÔS9JK\∆\Íå\œXO{Jbs\‰s™ª\«\ÂZG:õ˝∑Ø\‡Ñ\Z\Z∫y\·¬Ö\'\Ï\»g\È¨±xn\ÁO\€◊ïMñ\“ü8/(ä˛ˆy≥)KCñ\Í&\Ó†\ƒ˚u\ÌNHú•%\√ïâíqÜ:c\≈3˛\ÎjoYå\Á|\∆É&˚j˜\ÿ.∂\Ô}Ngd6¨\◊@\"qOw˛¸˘£´V≠\Zù\Íé\‚ÖDc\œ\ÈL§≥+[iò\Ì\…X\Z\ÃPY\…8Cùû\‚∆é|é?\Ïâ∑ZäOèäß\’\ƒ\—\Ëxˇ\ﬁ_\„˚8=\Œ\Ô}À§±åŒñı\ZH¶^|>\\\ﬂ\ﬁ3<<<rˇ˝˜èNˆ}>\„}:oª\Ì∂mÒ\Íız\€˚i\Ô;+•°¨d,\rf®¨dú°N´xÜXgcΩmï\»WöYµ^I\≈[i\‘“æ∫Ñé.]∫Ù\‡ö5k>˘‰ìá˜\Ï\Ÿ3Z˜\Ã\—]ªvz¸Ò\«_¸\Õo~Ûèã.∫h˜‹πs\„û˜æ\Ÿ¯JCY\…X\Z\ÃPY\…8Cù	ägWwΩû†T+≥rΩíãOÆ®®ïu±å\n\ÌØsxl—äØ˚\«N_9õ?\·Bi(+KÉ*+g®så\‚\ŸeΩòîÜ≤í±4ò°≤íqÜ:ì(û\0\ÃJCY\…X\Z\ÃPY\…8C\≈`0(\re%ci0Ce%\„uOÄ¡†4îïå•¡ïïå3\‘Q<É\“PV2ñ3TV2\ŒPGÒJCY\…X\Z\ÃPY\…8C\≈`0(\re%ci0Ce%\„uOÄ¡†4îïå•¡ïïå3\‘Q<É\“PV2ñ3TV2\ŒP\Á?\≈ÛDÄR)\re%ci0Ce%\„u˙\À\„ÒÄR)\re%ci0Ce%\„0 îÜ≤í±4ò°≤íqÜ\0JCY\…X\Z\ÃPY\…8C\0•°¨d,\rf®¨dú!\0Ñ\“PV2ñ3TV2\Œ\0Bi(+KÉ*+gÄ°4îïå•¡ïïå3¿ÄP\Z\ J\∆\“`Ü\ J\∆`@(\re%ci0Ce%\„0 îÜ≤í±4ò°≤íqÜ\0JCY\…X\Z\ÃPY\…8C\0àµk\◊V\ËªÒíôO˝w8ú±4ò°ríuÜ\06lŸπsg\ﬂ\rò\Ã|∂o\ﬂ˛øui\ÿ\ﬂ˛ï\Œïì¨3¿ÄX∑n\›w÷Ø_?∫c«éQG≠NN\Í\ﬂ˚H]∂◊Ö!éV-iˇçJgÜN~≤\œ\0$n®\‚(I<D\'\'-Ò˚_\“˛\€d?˚\ÿˇ°˝ˇíôK\Í\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0¶\Ëˇ\0&®ÃìÚÄ\ﬂ\‡\0\0\0\0IENDÆB`Ç',1),('2',5,'source',NULL,'{\"resourceId\":\"1\",\"properties\":{\"process_id\":\"process_5\",\"name\":\"\",\"documentation\":\"\",\"process_author\":\"admin\",\"process_version\":\"\",\"process_namespace\":\"http://www.activiti.org/processdef\",\"executionlisteners\":\"\",\"eventlisteners\":\"\",\"signaldefinitions\":\"\",\"messagedefinitions\":\"\"},\"stencil\":{\"id\":\"BPMNDiagram\"},\"childShapes\":[{\"resourceId\":\"sid-8EFE6380-4EF9-468C-9140-4C9B6D597183\",\"properties\":{\"overrideid\":\"start\",\"name\":\"ÂºÄÂßã\",\"documentation\":\"\",\"executionlisteners\":\"\",\"initiator\":\"\",\"formkeydefinition\":\"\",\"formproperties\":\"\"},\"stencil\":{\"id\":\"StartNoneEvent\"},\"childShapes\":[],\"outgoing\":[{\"resourceId\":\"sid-948ED0F7-D92B-47BB-AB90-E52F0170609B\"}],\"bounds\":{\"lowerRight\":{\"x\":225,\"y\":195},\"upperLeft\":{\"x\":195,\"y\":165}},\"dockers\":[]},{\"resourceId\":\"sid-9445F432-E485-496C-BF25-A086CFC79391\",\"properties\":{\"overrideid\":\"input\",\"name\":\"ÂΩïÂÖ•\",\"documentation\":\"000\",\"asynchronousdefinition\":\"false\",\"exclusivedefinition\":\"false\",\"executionlisteners\":\"\",\"multiinstance_type\":\"None\",\"multiinstance_cardinality\":\"\",\"multiinstance_collection\":\"\",\"multiinstance_variable\":\"\",\"multiinstance_condition\":\"\",\"isforcompensation\":\"false\",\"usertaskassignment\":{\"assignment\":{\"assignee\":\"admin\"}},\"formkeydefinition\":\"\",\"duedatedefinition\":\"\",\"prioritydefinition\":\"\",\"formproperties\":\"\",\"tasklisteners\":\"\"},\"stencil\":{\"id\":\"UserTask\"},\"childShapes\":[],\"outgoing\":[{\"resourceId\":\"sid-DEAA9ADE-493B-4942-B3FA-9FE7FCE01E57\"}],\"bounds\":{\"lowerRight\":{\"x\":370,\"y\":220},\"upperLeft\":{\"x\":270,\"y\":140}},\"dockers\":[]},{\"resourceId\":\"sid-948ED0F7-D92B-47BB-AB90-E52F0170609B\",\"properties\":{\"overrideid\":\"\",\"name\":\"\",\"documentation\":\"\",\"conditionsequenceflow\":\"\",\"executionlisteners\":\"\",\"defaultflow\":\"false\"},\"stencil\":{\"id\":\"SequenceFlow\"},\"childShapes\":[],\"outgoing\":[{\"resourceId\":\"sid-9445F432-E485-496C-BF25-A086CFC79391\"}],\"bounds\":{\"lowerRight\":{\"x\":269.15625,\"y\":180},\"upperLeft\":{\"x\":225.609375,\"y\":180}},\"dockers\":[{\"x\":15,\"y\":15},{\"x\":50,\"y\":40}],\"target\":{\"resourceId\":\"sid-9445F432-E485-496C-BF25-A086CFC79391\"}},{\"resourceId\":\"sid-243B4E4F-36F9-45E3-AC70-155046E26D96\",\"properties\":{\"overrideid\":\"audit\",\"name\":\"ÂÆ°Ê†∏\",\"documentation\":\"011\",\"asynchronousdefinition\":\"false\",\"exclusivedefinition\":\"false\",\"executionlisteners\":\"\",\"multiinstance_type\":\"None\",\"multiinstance_cardinality\":\"\",\"multiinstance_collection\":\"\",\"multiinstance_variable\":\"\",\"multiinstance_condition\":\"\",\"isforcompensation\":\"false\",\"usertaskassignment\":{\"assignment\":{\"assignee\":\"admin\"}},\"formkeydefinition\":\"\",\"duedatedefinition\":\"\",\"prioritydefinition\":\"\",\"formproperties\":\"\",\"tasklisteners\":\"\"},\"stencil\":{\"id\":\"UserTask\"},\"childShapes\":[],\"outgoing\":[{\"resourceId\":\"sid-C3E9BD50-6A4F-4158-A538-50C439E1A6F4\"},{\"resourceId\":\"sid-2DEB0DBB-99FE-4CD2-A7E1-EADB10819259\"}],\"bounds\":{\"lowerRight\":{\"x\":515,\"y\":220},\"upperLeft\":{\"x\":415,\"y\":140}},\"dockers\":[]},{\"resourceId\":\"sid-DEAA9ADE-493B-4942-B3FA-9FE7FCE01E57\",\"properties\":{\"overrideid\":\"\",\"name\":\"\",\"documentation\":\"\",\"conditionsequenceflow\":\"\",\"executionlisteners\":\"\",\"defaultflow\":\"false\"},\"stencil\":{\"id\":\"SequenceFlow\"},\"childShapes\":[],\"outgoing\":[{\"resourceId\":\"sid-243B4E4F-36F9-45E3-AC70-155046E26D96\"}],\"bounds\":{\"lowerRight\":{\"x\":414.15625,\"y\":180},\"upperLeft\":{\"x\":370.84375,\"y\":180}},\"dockers\":[{\"x\":50,\"y\":40},{\"x\":50,\"y\":40}],\"target\":{\"resourceId\":\"sid-243B4E4F-36F9-45E3-AC70-155046E26D96\"}},{\"resourceId\":\"sid-EE9714E8-C138-4C26-AC3C-F5FA8D1E660C\",\"properties\":{\"overrideid\":\"\",\"name\":\"ÁªìÊùü\",\"documentation\":\"\",\"executionlisteners\":\"\"},\"stencil\":{\"id\":\"EndNoneEvent\"},\"childShapes\":[],\"outgoing\":[],\"bounds\":{\"lowerRight\":{\"x\":588,\"y\":194},\"upperLeft\":{\"x\":560,\"y\":166}},\"dockers\":[]},{\"resourceId\":\"sid-C3E9BD50-6A4F-4158-A538-50C439E1A6F4\",\"properties\":{\"overrideid\":\"\",\"name\":\"\",\"documentation\":\"\",\"conditionsequenceflow\":\"\",\"executionlisteners\":\"\",\"defaultflow\":\"false\"},\"stencil\":{\"id\":\"SequenceFlow\"},\"childShapes\":[],\"outgoing\":[{\"resourceId\":\"sid-EE9714E8-C138-4C26-AC3C-F5FA8D1E660C\"}],\"bounds\":{\"lowerRight\":{\"x\":559.375,\"y\":180},\"upperLeft\":{\"x\":515.390625,\"y\":180}},\"dockers\":[{\"x\":50,\"y\":40},{\"x\":14,\"y\":14}],\"target\":{\"resourceId\":\"sid-EE9714E8-C138-4C26-AC3C-F5FA8D1E660C\"}},{\"resourceId\":\"sid-328F220C-A4F8-4EBE-9A7E-4243BCBB4A03\",\"properties\":{\"overrideid\":\"end\",\"name\":\"ÁªìÊùü\",\"documentation\":\"\",\"text\":\"\"},\"stencil\":{\"id\":\"TextAnnotation\"},\"childShapes\":[],\"outgoing\":[],\"bounds\":{\"lowerRight\":{\"x\":660,\"y\":205},\"upperLeft\":{\"x\":560,\"y\":155}},\"dockers\":[]},{\"resourceId\":\"sid-2DEB0DBB-99FE-4CD2-A7E1-EADB10819259\",\"properties\":{\"overrideid\":\"\",\"name\":\"\",\"documentation\":\"\"},\"stencil\":{\"id\":\"Association\"},\"childShapes\":[],\"outgoing\":[{\"resourceId\":\"sid-328F220C-A4F8-4EBE-9A7E-4243BCBB4A03\"}],\"bounds\":{\"lowerRight\":{\"x\":559.2500542490575,\"y\":179.47395889842767},\"upperLeft\":{\"x\":515.4999457509425,\"y\":179.01822860157233}},\"dockers\":[{\"x\":50,\"y\":40},{\"x\":1,\"y\":24}],\"target\":{\"resourceId\":\"sid-328F220C-A4F8-4EBE-9A7E-4243BCBB4A03\"}}],\"bounds\":{\"lowerRight\":{\"x\":1200,\"y\":950},\"upperLeft\":{\"x\":0,\"y\":0}},\"stencilset\":{\"url\":\"stencilsets/bpmn2.0/bpmn2.0.json\",\"namespace\":\"http://b3mn.org/stencilset/bpmn2.0#\"},\"ssextensions\":[]}',NULL),('3',1,'source-extra',NULL,'âPNG\r\n\Z\n\0\0\0\rIHDR\0\0\∆\0\0\0\0\0ä;\—Ò\0\0\0 cHRM\0\0z&\0\0ÄÑ\0\0˙\0\0\0Ä\Ë\0\0u0\0\0\Í`\0\0:ò\0\0pú∫Q<\0\0\0gAMA\0\0±é|˚Qì\0\0\0sRGB\0Æ\Œ\È\0\0\0bKGD\0ˇ\0ˇ\0ˇ†Ωßì\0\0\0	pHYs\0\0\ƒ\0\0\ƒï+\0\0\Z&IDATx\⁄\Ì\›òUeΩ053¿pÑP·ì¢\ÀQèYñ©©\‡ıÙh^-hVv\·ò\ÈW=\÷\Á\Â§OYdV\ÂçÙx\‰¶i\È£(ëäôFäö\‚\rAPÑFÆ\»eˆ˜˛˜\ÏM\€aFaòÅë˝˚=\œ\À^{≠µ\◊0\Ô˛œªˇ˚]\ÔzWñ\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\–\÷U®†%\‰rπˆØºÚ\ y+WÆ¸\œ\⁄\⁄\⁄≠]ª∂:m\ÃVjﬂæ˝˙v\Ì\⁄-\Î–°\√C©n8x\‡\'\ƒb$\∆@5oﬁºØ\÷\‘\‘¸$}\Ëv\È”ßO÷£Gè¨c«éY˙ V9[)%ÜŸö5k≤%Kñd,X_UUıxZ˜Ö!CÜ\ÃCà!êmD.ó´ú;w\Ó¯9s\Êú“Ø_øl˜\›w\œ*++UL+©´´\À\Êœüü•:_ï\Í˛îî\ÿ\‹+Ü(˜Çñ§ıö-öTN\Ÿgü}≤æ}˚JhZª¡Nııº\Ôæ˚vN\ÀßLôr¨¢\‹cZRï*\0ö#N}œû=˚ˇFB”≠[∑M∂Ø^>/[0\„ˆ¨\ÊÖ?fã\Á<î\’.}%\Î–•W÷æ∫õ\ \€J\’\’\’Y˜\Ó\›;\‘\‘\‘|v¯\·∑\›|Û\ÕK\≈\ÂC\–\‚_U∞•\‚\"©\ZßæKh÷¨XêΩˆ\ƒ\rŸ™%≥≤∫\r\ÎÚ%ñc]lc\ÎEΩß˙ﬂêﬁãQbàrå!êmB\ÃP[[\€%∆É6f…ú©˘D¶°X\€h)©\ÈYYYy§IìˆCî[Å\ƒhVÆ\\9ºw\Ô\ﬁMé]˝\ÊkMæˆù∂±Ö\rx™ˇ]w\›ıçÙx≤¢\‹b$\∆@õP[[; ¶\”jJ]›∫fmcÀ•\‰≤kEE≈ëbàrã!h\r\ÌT∞•÷Æ]€±SßN*¢\r\Ë‹πsè˜b[.Ü\ƒHåÅ¬∫u\Î*⁄∑oˇ{T4s[™Cá]\”C\'1Dπ\≈¥C)Ä-ñ\ÀÂ≤ää¶ììùvi\÷6∂\\\·}®Cî[Å\ƒxO\Ë±\Á¡Ye’¶∑Ú≠j\ﬂ)\Î\ŸˇPÑ$\∆@yÿ©˜>Yó]?º\…˙\Œ=d]{˝õ\nBc†L\‰rŸÜµ´6Yù_ó∂Å\⁄\"\ﬂ-j\Ì™\≈\Ÿ?ü˚}∂j\Ÿ\‹M∂≠Z:;{ıâ\Î≥>{üîu\Ë\‹Se!Üh∂a√Üµ\‰7§K«çwôZEb¥å\\.[˙⁄£\Ÿ\¬YìΩc\Ÿ\∆\ƒ&%;s˛vM\÷k¿ë\Ÿ.˝≈ï?\Í1D≥§d÷õè\ƒh[ﬁ©áØ1ëÙ‘ºtO∂\‚çz˛C@õaå1∞’¢˜nsöR≈û?CÄ\ƒ\ÿ!º\”i\Ô\÷|-bH-\…P\n`´˝\€ó´\ƒûß\«\0\0$\∆\0\0 1\0\0â1\0\0îrÒlÅ\\.W˘\ÿcèù0s\Ê\Ãs_y\Âï˝W≠Z\’e˘Ú\Â\’Kó.≠\ÿy\Áù\◊w\Ì\⁄uuUU\’\ÈÒ\ŒŸ≥gˇ|Ãò1Ø©5Ä\Ì\„\‘SO\›#µ\…«•∂˚?***˙§Uª•\“7ïy©,H\Îˇô\÷ﬂ≥a√Üª&Nú¯∫\ZCbõówº\Ôæ˚~qÒ\≈èX∞`A˚˝˜\ﬂ?;˙Ë£≥^Ωze)!\ŒzÙ\Ëë-Y≤§›≤e\ÀvZ∏p\·N”ßOø`Œú9\Áè9r\Óö5kæz\›u\◊›ß∂ç°Cá^YY∑x>$ûWlzw\ƒHé˚\÷óí\Á\—√Ü\r{∏ÆÆ\Óí	&<†%\∆@{Ï±Ø_~˘\Â£/^\‹Ò¥\”N\Àò•wì˝\"9éÚÅ| ;¿≥\‘¿VNõ6\Ì˝c«éΩ˚k_˚\⁄3\Èıßé?~ñ\Zh©çêí›∏\„\À1\Õx˘!©mˇsJê\'\ÂrπohØ%\∆@â\‘0V\‹ˇ˝∑å3\Êå\œ}\Ós\ŸQGïUUUmˆ\Î#y˛‘ß>ïp¿\Ì\“q>>a¬ÑßO?˝ÙS¢<Y\Ì¥¨°Cáë\⁄\›\€R\€›Ω∏Æ}˚ˆ\Ÿ\ﬁ{\Ôù\Ô\–\Ëﬂø\ÈælŸ≤eŸú9s≤i”¶e\œ=˜\\∂n\›\∆\≈ìíÎøß\„\rM\Ìˆü‘¨\ƒ$\≈))æ\„é;õ<yÚ¿Û\œ??˚\»G>\“\ÏcE2}\Ï±\«FOróQ£F˝\·å3\ŒyÎ≠∑ﬁ†ñZ∆∞a\√\ŒN\…Ïµ©\ÌÆ*∂ªávXv\ )ß\‰ì\·ÜJ\œ\r<8ü$\ﬂv\€mŸÉ>òmÿ∞!v\Ÿ%%\Ÿ˜¶\„~e‹∏q7™\·ÚaV\nhDÙß2\“K/›™§∏T\Áä+Æ\ËT]]}uÙl®eÄ≠\ÌiiRºÎÆªfó_~yv\Œ9\Á4ö7&ˆã˝\„uÒ˙Ç\Ë<¸≠ˆZbe\ÌØ˝\Î\◊\Ô∏\„é3æ˘\ÕofΩ{˜n\—c\«ÒæÛù\Ôt\Èÿ±\„\ƒSO=ıÉj†˘¢m◊Æ\›\ƒbR<`¿Ä\ËÄ\»õhéx]º>éSLé+++\«kØ%\∆Pñbˆâ?ˇ˘œ£é?˛¯\Î)n(∑\‚\‰ìOé±o◊®qÄÊ´™™˙E]]]æ[8zzø˝\Ìog›∫u€™c\∆\Î\„8%=\«=\‚\Á®mâ1îùIì&˝|—¢E\„Bª\÷Ù\Ÿ\œ~v\ÁŒù;\"¶R\Î\0\Õvl!A\Œ.∏\‡Ç≠NäKì\„8^\…\◊\«jØ%\∆PV\‚\Êè<Ú\»Y1%€ñ\Ã>\—q¸#F¥Ø¨¨¸\Ô\ÌÒªû~˙\È§2»ªézw´WØ\Œ÷Ø_ˇé˚,\\∏∞tVÉç\ﬁz\Î-¡≤\rb(.¥k\Óâ¶\ƒÒ\‚∏¶\Ì\‘^≥môï\n}Ù\—\œ\’\‘‘¥èi}∂ÖAÉu=zÙæqg¶m}«•Ù%`T<6¨¶¢¢\‚\”\‚mc«é}tGxgÕö\„ƒõ\Ó\r®¨\Ã>ˇ˘\œo≤~∆å\Ÿ}˜›ó}\Î[\ﬂÚ\«P\Ê1\‘\–»ë#≥è¸\„\Ÿ◊ø˛ı&˜9˜\‹s≥¸\‡˘©¡äÆπ\ÊölÒ\‚\≈\Ÿ%ó\\\"`\ﬁ9Ü¶∫5-é\ﬂ\‹äv≥∏S≤\≈\Ï≠!é;u\Í\‘‚óûÉ∂G{ç\ƒ∂ãô3g~->¸\ZªyGkàüÛ\·x\ﬁs\œ=w|z:z;˝\⁄\ÔKNë	~´úHR~˜ª\ﬂ5ö\Ô±\«1î&[æ|y≥ëPæ1C©\“\Ô¥ÒyÙ«ó¶;\ÔºÛm˚Eº4<ªîæ\\g\◊]w]~y—¢E\Ÿ\Ôˇ˚ç˚z\Ë°\Ÿ!á\"b6\’+\’˜y\ÈÒº\ÕMíS˚y|qyü}ˆ\Ÿ\Ï\Ÿ\'∂T7éˇ\‘SO\≈”ä\¬\œ\Ì-ì\√\Ô\’W_˝\ÿGl\€Yy:Ë†ä\Áü˛\»6\“\–\Ó	\Œ˚\ﬂˇ˛,˝üõLåªt\È\“\‰\‡w‹ë?u\Zw.åπß)\œJì\Ÿ_˛Úóçœãg >˝\ÈOo\\7\Ôâ\‰˘\—G\Õ.æ¯\‚¸\r\"æÙ•/e\'ùtR∂\Áû{\Êø`˝\Ê7ø\…\œpP¸≤\›Z\…[9&\…i˝ë\≈\ÂO~Úì≠˙ä\„\„\‚œïKåa«∑b≈äÆ\Ô{\ﬂ˚∂\È\œ\Ï◊Ø_di˝$8-+ì\∆\ƒ\È\–wJN\"i\ﬁ}˜›≥Û\Œ;/ü w\Í\‘\…F\∆PÙ\«\ÈÛ¢{\ÔΩ7ˇX:\Œ¯¯C÷Æ]ª\Ïc˚Xv˝ı\◊\Á{Ç\„f@Ò\Â:b,ñch\≈\·√≥n∏!ü0\Ô≤\À.mˆw^∞`Ai(é˘xh‹∏q∑•ıá•áC[a\€\'\…\È˘\ÃÙ|\◊Ù∏wzû\ﬂ)n\“—ö\Zøü?mâ1îÖ\ÂÀóWo\Îùîx\≈|@ªî| µıgez|*}âhìˇ\—7\ﬁx#ªˆ\⁄kõ\‹>oﬁº\Ïı\◊_\œO\‚ü~ó\ÏÉ|˚‘§µµµ\Ÿ#è<íOn\‚8ë ∑u+WÆÃû~˙\ÈLµúàÉr3bƒàF∑\ﬂx\„ç\Ÿ˜øˇ˝\‚\ﬂp∂t\È\“¸ä—£Gg\'ûxbvı\’W\Á\Ô¢C≥bø\'ü|2;˚\Ï≥\€tzD/-¨z0ΩOáñ\ÏrX°¥Ù∂-QLíÛRRº°∏\‹\⁄\ÌvÉ\„\Ô\Ó\”Rbe!}V\Ï¥\”N\€Ùg¶\Ã¯\ÏRÚÅ\‘\÷uM\Â\‡i”¶≈îsm\Ó?=æ|pˆ\‚ã/f{\ÌµW~\›ƒâÛ¸gùuVˆô\œ|&˚\ËG?öü£:n\€Påçû\‚ü¸\‰\'˘}£∑Ø≠ü˛éD˛Ò\«\œ\ƒP\À\«RÒ\ÃC±ß∑¯º\·pú\Ëaé1\Í˚\Ôøv\”M7\Â\◊]x\·Ö˘\€\«\Ô˘¿\ƒ\›4≥\√?<\Î–°Cõå°˘Û\ÁgM$¥\rµ∆∂\Ê\ÿ8∏ªµ\€\Ì\«\Ô\È\”Rbe°s\Á\Œ\ÎW¨X\—n[û\ÓLI\⁄¸±c\«FC{\’6˛u7\'â*\Œ3U]≤\Ó\ÕTû8p\‡¡m5ô)\‹z;\€oø˝≤˛á\ŸO<ë]y\ÂïŸè~Ù£\Ïû{\Ó\…˜Nò0aì\ƒ8ÜY\ƒ\Î~ı´_\≈ó\ÏÑN\»˜,è\Z5™M\«\ÌAîΩÙ\“Kë_*ÜZNÙ\0áSÃû=;ˇX|g&äV≠ZïO,£w¯\Ã3\œ\Ã\'œëH˝ˆ∑ø\ÕAããˆ\"1é^\‰!CÜà°-è°øRóJá1‘©∏.\Œ>¥fª\›\‡\Ï\∆büñc(]ªv]ΩlŸ≤ù∂ebºzı\Í%\—–é7\Ó≤m˘ª6¨\—§äääπ˙\ÀÒªï$3Ø¶ı∑e%\„Cø¯\≈/\Ê\⁄\Í˚∏\€nª\Â{\Á¢\◊˜\·á\Œ_D\„@„Ç∫HLz\Ë°Fo˚G/s1yπË¢ãÚ\…uÙ4\«U\Èm8nÛˇ\œ/ºPµ†fân\·\Ô4ˇX|˛\Ï≥\œn\‹/\∆:4?CEå9é≥O|\‚ü\»&Oûú=ˆ\ÿc\Ÿeó]ñ\›}˜\›˘mb\Ë\›c(æP•xYü¬®KIûÚ∂JØ˝{Z\Œ_uó\⁄\ÌVMå\„¯%\Ê˚¥îCY®ÆÆ˛gMM\ÕN1´¡∂Ú\‚ã/\Œ/Ùàl7•âLzÿ©©D\ÊΩb√Ü\r˘ã•b&Åxåû·£è>:\ﬂ<fÃò¸)„ÜâqÙ \«T•\\ı\Í\’+ªÍ™´ÚME2›ßO$eC!\∆\‹\∆É\“D∏\ÿS¸è¸c\„~Ω{˜\Œ\'\∆!\ŒZ\ƒ¯‚∏ùpå9éc\ƒä8¡;*MÜ´\”cıª\ƒ\–k\≈\ƒ8zÙ[≥\›.û1(˘πHå°,\„?>˘\‰ì\ƒ4L\€ î)S⁄•Ä{∑”Øß\'+wîD&ˇ\…˙\÷[˘û\·\Ë˝äãûbö•ü˝\Ïg˘mqÜd\'ü|ræ8ÜVÙ\Ì\€7?gÒ-∑‹í\Ô—ã\Á•\‚Ωä1h–†l¸¯ÒŸ∂∫˘\À{\»C!Ü\„\ƒ\\\ƒ\≈\ÈŸä\√ib\‹p\·m>1∂∏T\Ã\\S∏˝¯\«?\Œ˜\Z\«Öü˛Ùß˘Ò‘∑\ﬂ~{æwñMch3ì\·ç“æì\”~\'\∆rå±o\Õa*Ö1¸Æ∑LbeaŒú9?\Ìµ\◊ŒØ´´´\‹7˘H?ßf—¢E§\«/mß_πrGIdJæ\‹\‰O]w\Ï\ÿ1?gÒëGô?\r\ZcåC<èû¿\ËIéºó_~9\ÊØ\Œ_ \’Tèp±àÑ:z¢\ŸÒc(ƒ¥ç•S76<\√âsSbúz\Ã|cå˜\›w\ﬂ¸∫JÒ\À_˛2?{Hå\Áe\Îc(µõ¨™™˙u,\«]+\„\Ôº5.îç\„>˚\Ï≥q6$\ÊÜ\À\≈\œıñIå°,å3Êµë#Gæöí§˛€¢\◊x\‚ƒâJ{nè€ã¶°\Ë˙˙\ÎéxáªHäCœû=Ûc<ä\€\«|˝M≥bLqÙøõ\Ëm¶|b®°/˘\À\Ô∫OÙá¶nˇ∑ï¶\Âb(\⁄\Õ8£\‚\¬\Ÿær\Œ9\Á¥¯ˇ1éª~˝˙ä\¬\”G\‹ZbeeÕö5\Á¶F˙\Ó8†]\√[Ω∂§\r6ÃªÛ\Œ;¨´´˚Úˆ¯=\”\Ô¯S\Ô6bà%Übjºòºˇ˛-ˆˇõ3gN\Ã(R\Ï-é^ÍãΩk;æJU\0ˇr\›u\◊›ó\Zøg\Óøˇ˛÷ºb>w\’UWEoÒ\Ã	&<†\÷ö-çFuä±\‹q+\Óñ\«5jT˙8®+ˆﬂ´ΩñCY™©©:q\‚\ƒ⁄∏¿¶5Lù:ı¶Oü~Lj\»ˇKm4_°çi/≥Eã\Â/z\‹\⁄\‰8^\ÂïW\Ê/^\\ÃëñhØ%\∆P∂RR¸Úö5kNJ\r\Ï\Í“â¸[\¬‹πs\Ô=zÙëuuug\∆\œQ\€\0[\◊^ßˆÙ¥¨0\Ì\Â¨Y≥≤\Ô}\Ô{˘a\ÕØªË¢ã\Ífœû]\Ï)^\«\◊^Kå°¨ç;vrJéG~˜ª\ﬂ]\—R=\«SßNΩ9oˇ¥¯˝	&¸I-lΩB{˙ïbr=\«1]c\ÃM\ﬁ\‡\ÊMä˝bˇîTóˆ\«Òæ¢Ω./.æÉ&\‹z\Î≠7:t\ÓW\\1ÒîSNâyHwn\Œyq°]å)û>}˙±uuu_\–\»¥¨q\„\∆›ò\⁄\ÎW+++\'§ßªƒò\„)S¶\‰o⁄≥˜\ﬁ{\Á\Á èÛbJ∑∏\Èœí%KÚ\…pÙ\«tç3f\Ã\»fü(ˆ/M\ÌıP\Ìµ\ƒh\–q\⁄iß}r¸¯ÒøæÎÆªˆ1bD˚AÉuﬂúyéS£˙FL\…v\Áùw\∆]!˙\‰rπÉ\“Òf©UÄVkØ®®®∏&==&\÷\≈Tn1tîwQQ≤<)µ\◊\ﬂ\–^KåÅF§§8\Z«£áz¯5\◊\\s\Ÿ\Ë—£˜˚–á>Ù\Í!áR—∑o\ﬂn={ˆ\ÏëJnaΩ%3g\Œ|} î)\“Ú\Èu˝bJ6W3l≥ˆ˙\ÿhØ+++/KÀál¡\ÀN\Ìı%\⁄kâ1∞yΩ\—X>pÍ©ß\ÓÒ\¬/˜¸Û\œˇGEEEüBOC\‹K8≥\’\Ârπ¥∫\‚6l8\«d\0€∑ΩÆ™™:.µ\À\≈ˆz∑B{=/ïi˝?\”˙{R{}óˆ\Zâ14C°Ò¸M°\0†ΩfaV\n\0`G—πëu7\„uUchY1e\≈nç4“ΩU\r@´ãam\À\ÍáJî∫π¡Ûˆ©ÙoP\∆6≤ÆÉ*-?ÜR¿;7≤o\∆~?K%nµÙÅT.JÂ¨ím#ç\ÎT\'@´äô(û\ \Í\«èHexa}øTä\‘˝8ï\ÁR\”\‡µ}\ZYs#ø§Z%\∆@Ω\\*ñë\ ˜RYQx=ˇYX~´¯\·BÉ[Ω\≈g¶2(ï/¶2.ïZ\’\n\–*˘LtLúë\ ßrG*á∂ΩP≤\\t|*=KûO\…\ﬁﬁ©∑ô^ÆZ%\∆¿\€=Xx|3ïG\nçeXU≤-|)ïˇóJ\‹C˙Ö\∆˘\ÿT~RH¶\„uW∂–≤.\ÃÍá≤\≈Ev]≥˙3yá∂u.Y~<ïïÖ6::.f\÷Gí|Ia9:9nO\Â*\’*1öÁ∫¨˛4^Ù_ù\ >©úõ\ 5ÖÜ∫:ïìRπ1ï\È™†E˝O*˜•Ú\ÎB\“\…Ò°Öm7î,øXHå≥B{|ca9\Œ\Ï{å££c\'U*1\ﬁnp°G°ÿÉInq\ÿD\Èx¥\"q*›≤˙πå˚gıC/¢\„µTñfı=∆øLeoâ1@ã[THr\ÔN\ÂË¨æg8zÉ€ó\Ï\Ì˜Çí\Ággˇ∫Hiõ^\Ï1Fbîàqi7ñèH\Â⁄ímCJ∂Ωú\ ^Y˝Evªfıcì#˘Q\“KQlî\œU≠\0-cÿ∞aπ´ézóóåm¸†¯g∆åküyÊôèeı\Ï\≈\ÿ\‚%jWbº\›¸Bâ+ö\„*\ÁK∂5c\‚Tﬁ¥¨~XE¯dVJØ\Ë˜™†eå7Æ¢ë\’1\r\Ìøgı:è/¥\√Eœ§≤o\…Û≥˙\Î@æù\ \ÔRπ ï\ÎS˘f*s‘∞\ƒh\\4ö∑l\∆~∑¶Ú\’TN\…\Í{ì\◊5h\\◊©JÄVù\Ágıg\Ïû\Œ\Í\œ\≈8\·\·%˚t*<\ÓUHàsÖ\ÌØ\÷ˇ9ïaY˝u\"±\ÔwSyB\’JåÅ˘J°\«\·¸wŸØcV?>-\∆\œN\Âˇ§“ΩA£º≥\Íh1µfå/.ùbmAÉ}^/<\∆0âò\œxj#€¢˝é©\‹b\ÏÒB\’*1˛•V^\Ã6±æâ∂(\∆\«<òø+Y˜\œ˚\ÃW•\0≠\‚ıF\÷\÷\‡yÒ∫êöB)5§¡ÛáU©\ƒxª9©ú\÷ƒ∂Üç\Ë}ç\ÏsLç2\0\–U™`KUTTdπ\\NE¥\È}à\»lCî[Å\ƒh⁄µk∑~\›:\◊∂oΩıV\‹m±FQn1c†≠$5\ÀVØ^≠\"⁄Äï+W\∆\≈BØä!\ -Ü@b¥	:txh\…Ûﬂ∑Û\ÊÕ´\Õ\Ârì\≈\ÂC 1⁄Ñï+W˛∞¶¶f}]]ù\ ÿéR˝\œ^æ|˘\'\“\„\Ìbàrã!êm\¬\‡¡Éü®¨¨||˛|3\–mO3f\Ã¯Kz¯˚1\«Û¥¢\‹b$\∆@õ±v\Ì\⁄/Ãù;∑v˘Ú\Â*c;x\„ç7˛w\È“•Gß\‰Ú\€bàrç!hiU™\0héõoæy\È\·√üZ∏p\·	›ªw\ÔP]]≠R∂aBÛ\¬/N	\Õ\ŸCÜô&Ü(\◊â1\–f\‹t\”M3\œ<Û\Ã\È\ÈCˆ∏\\.W€≠[∑\Œ1?-≠#∆É>˚\Ï≥wÕõ7\Ô\ËT\œgq\ƒì\ƒ\ÂC–í¥>¿Võ2e Äî‘åJ¥áÙ\Í\’kAü>}∫w\Ïÿ±Guuuµ≥UVÆY≥famm\ÌK)ëYı\ÊõoL\Î˛ßæá2K!Ü@b¥Qì&M\⁄/}\‡ûúíõ£Rí≥Gz\Ï´V∂N™\«y©_Oè˜\«\Ã;˙ERbH\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0¿\€¸Ü\Á5¿\08\«A\0\0\0\0IENDÆB`Ç',NULL),('9',1,'ËØ∑ÂÅáÊµÅÁ®ã.bpmn20.xml','8','<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<definitions xmlns=\"http://www.omg.org/spec/BPMN/20100524/MODEL\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:activiti=\"http://activiti.org/bpmn\" xmlns:bpmndi=\"http://www.omg.org/spec/BPMN/20100524/DI\" xmlns:omgdc=\"http://www.omg.org/spec/DD/20100524/DC\" xmlns:omgdi=\"http://www.omg.org/spec/DD/20100524/DI\" typeLanguage=\"http://www.w3.org/2001/XMLSchema\" expressionLanguage=\"http://www.w3.org/1999/XPath\" targetNamespace=\"http://www.activiti.org/processdef\">\n  <process id=\"process_5\" isExecutable=\"true\">\n    <startEvent id=\"start\" name=\"ÂºÄÂßã\"></startEvent>\n    <userTask id=\"input\" name=\"ÂΩïÂÖ•\" activiti:assignee=\"admin\">\n      <documentation>000</documentation>\n      <extensionElements>\n        <modeler:initiator-can-complete xmlns:modeler=\"http://activiti.com/modeler\"><![CDATA[false]]></modeler:initiator-can-complete>\n      </extensionElements>\n    </userTask>\n    <sequenceFlow id=\"sid-948ED0F7-D92B-47BB-AB90-E52F0170609B\" sourceRef=\"start\" targetRef=\"input\"></sequenceFlow>\n    <userTask id=\"audit\" name=\"ÂÆ°Ê†∏\" activiti:assignee=\"admin\">\n      <documentation>011</documentation>\n      <extensionElements>\n        <modeler:initiator-can-complete xmlns:modeler=\"http://activiti.com/modeler\"><![CDATA[false]]></modeler:initiator-can-complete>\n      </extensionElements>\n    </userTask>\n    <sequenceFlow id=\"sid-DEAA9ADE-493B-4942-B3FA-9FE7FCE01E57\" sourceRef=\"input\" targetRef=\"audit\"></sequenceFlow>\n    <endEvent id=\"sid-EE9714E8-C138-4C26-AC3C-F5FA8D1E660C\" name=\"ÁªìÊùü\"></endEvent>\n    <sequenceFlow id=\"sid-C3E9BD50-6A4F-4158-A538-50C439E1A6F4\" sourceRef=\"audit\" targetRef=\"sid-EE9714E8-C138-4C26-AC3C-F5FA8D1E660C\"></sequenceFlow>\n    <textAnnotation id=\"end\"></textAnnotation>\n    <association id=\"sid-2DEB0DBB-99FE-4CD2-A7E1-EADB10819259\" sourceRef=\"audit\" targetRef=\"end\" associationDirection=\"None\"></association>\n  </process>\n  <bpmndi:BPMNDiagram id=\"BPMNDiagram_process_5\">\n    <bpmndi:BPMNPlane bpmnElement=\"process_5\" id=\"BPMNPlane_process_5\">\n      <bpmndi:BPMNShape bpmnElement=\"start\" id=\"BPMNShape_start\">\n        <omgdc:Bounds height=\"30.0\" width=\"30.0\" x=\"195.0\" y=\"165.0\"></omgdc:Bounds>\n      </bpmndi:BPMNShape>\n      <bpmndi:BPMNShape bpmnElement=\"input\" id=\"BPMNShape_input\">\n        <omgdc:Bounds height=\"80.0\" width=\"100.0\" x=\"270.0\" y=\"140.0\"></omgdc:Bounds>\n      </bpmndi:BPMNShape>\n      <bpmndi:BPMNShape bpmnElement=\"audit\" id=\"BPMNShape_audit\">\n        <omgdc:Bounds height=\"80.0\" width=\"100.0\" x=\"415.0\" y=\"140.0\"></omgdc:Bounds>\n      </bpmndi:BPMNShape>\n      <bpmndi:BPMNShape bpmnElement=\"sid-EE9714E8-C138-4C26-AC3C-F5FA8D1E660C\" id=\"BPMNShape_sid-EE9714E8-C138-4C26-AC3C-F5FA8D1E660C\">\n        <omgdc:Bounds height=\"28.0\" width=\"28.0\" x=\"560.0\" y=\"166.0\"></omgdc:Bounds>\n      </bpmndi:BPMNShape>\n      <bpmndi:BPMNShape bpmnElement=\"end\" id=\"BPMNShape_end\">\n        <omgdc:Bounds height=\"50.0\" width=\"100.0\" x=\"560.0\" y=\"155.0\"></omgdc:Bounds>\n      </bpmndi:BPMNShape>\n      <bpmndi:BPMNShape bpmnElement=\"sid-2DEB0DBB-99FE-4CD2-A7E1-EADB10819259\" id=\"BPMNShape_sid-2DEB0DBB-99FE-4CD2-A7E1-EADB10819259\">\n        <omgdc:Bounds height=\"0.45573029685533584\" width=\"43.75010849811497\" x=\"515.4999457509425\" y=\"179.01822860157233\"></omgdc:Bounds>\n      </bpmndi:BPMNShape>\n      <bpmndi:BPMNEdge bpmnElement=\"sid-DEAA9ADE-493B-4942-B3FA-9FE7FCE01E57\" id=\"BPMNEdge_sid-DEAA9ADE-493B-4942-B3FA-9FE7FCE01E57\">\n        <omgdi:waypoint x=\"370.0\" y=\"180.0\"></omgdi:waypoint>\n        <omgdi:waypoint x=\"415.0\" y=\"180.0\"></omgdi:waypoint>\n      </bpmndi:BPMNEdge>\n      <bpmndi:BPMNEdge bpmnElement=\"sid-2DEB0DBB-99FE-4CD2-A7E1-EADB10819259\" id=\"BPMNEdge_sid-2DEB0DBB-99FE-4CD2-A7E1-EADB10819259\">\n        <omgdi:waypoint x=\"515.0\" y=\"179.47916666666666\"></omgdi:waypoint>\n        <omgdi:waypoint x=\"560.0\" y=\"179.01041666666666\"></omgdi:waypoint>\n      </bpmndi:BPMNEdge>\n      <bpmndi:BPMNEdge bpmnElement=\"sid-948ED0F7-D92B-47BB-AB90-E52F0170609B\" id=\"BPMNEdge_sid-948ED0F7-D92B-47BB-AB90-E52F0170609B\">\n        <omgdi:waypoint x=\"225.0\" y=\"180.0\"></omgdi:waypoint>\n        <omgdi:waypoint x=\"270.0\" y=\"180.0\"></omgdi:waypoint>\n      </bpmndi:BPMNEdge>\n      <bpmndi:BPMNEdge bpmnElement=\"sid-C3E9BD50-6A4F-4158-A538-50C439E1A6F4\" id=\"BPMNEdge_sid-C3E9BD50-6A4F-4158-A538-50C439E1A6F4\">\n        <omgdi:waypoint x=\"515.0\" y=\"180.0\"></omgdi:waypoint>\n        <omgdi:waypoint x=\"560.0\" y=\"180.0\"></omgdi:waypoint>\n      </bpmndi:BPMNEdge>\n    </bpmndi:BPMNPlane>\n  </bpmndi:BPMNDiagram>\n</definitions>',0);
/*!40000 ALTER TABLE `act_ge_bytearray` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `act_ge_property`
--

DROP TABLE IF EXISTS `act_ge_property`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `act_ge_property` (
  `NAME_` varchar(64) COLLATE utf8_bin NOT NULL,
  `VALUE_` varchar(300) COLLATE utf8_bin DEFAULT NULL,
  `REV_` int(11) DEFAULT NULL,
  PRIMARY KEY (`NAME_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `act_ge_property`
--

LOCK TABLES `act_ge_property` WRITE;
/*!40000 ALTER TABLE `act_ge_property` DISABLE KEYS */;
INSERT INTO `act_ge_property` VALUES ('cfg.execution-related-entities-count','false',1),('next.dbid','32501',14),('schema.history','create(6.0.0.4)',1),('schema.version','6.0.0.4',1);
/*!40000 ALTER TABLE `act_ge_property` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `act_hi_actinst`
--

DROP TABLE IF EXISTS `act_hi_actinst`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `act_hi_actinst` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `PROC_DEF_ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `PROC_INST_ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `EXECUTION_ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `ACT_ID_` varchar(255) COLLATE utf8_bin NOT NULL,
  `TASK_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `CALL_PROC_INST_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `ACT_NAME_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `ACT_TYPE_` varchar(255) COLLATE utf8_bin NOT NULL,
  `ASSIGNEE_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `START_TIME_` datetime(3) NOT NULL,
  `END_TIME_` datetime(3) DEFAULT NULL,
  `DURATION_` bigint(20) DEFAULT NULL,
  `DELETE_REASON_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `TENANT_ID_` varchar(255) COLLATE utf8_bin DEFAULT '',
  PRIMARY KEY (`ID_`),
  KEY `ACT_IDX_HI_ACT_INST_START` (`START_TIME_`),
  KEY `ACT_IDX_HI_ACT_INST_END` (`END_TIME_`),
  KEY `ACT_IDX_HI_ACT_INST_PROCINST` (`PROC_INST_ID_`,`ACT_ID_`),
  KEY `ACT_IDX_HI_ACT_INST_EXEC` (`EXECUTION_ID_`,`ACT_ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `act_hi_actinst`
--

LOCK TABLES `act_hi_actinst` WRITE;
/*!40000 ALTER TABLE `act_hi_actinst` DISABLE KEYS */;
INSERT INTO `act_hi_actinst` VALUES ('15006','process_5:1:11','15001','15005','start',NULL,NULL,'ÂºÄÂßã','startEvent',NULL,'2022-05-08 05:38:45.454','2022-05-08 05:38:45.454',0,NULL,''),('15007','process_5:1:11','15001','15005','input','15008',NULL,'ÂΩïÂÖ•','userTask','admin','2022-05-08 05:38:45.454','2022-05-08 06:57:30.336',4724882,NULL,''),('17506','process_5:1:11','17501','17505','start',NULL,NULL,'ÂºÄÂßã','startEvent',NULL,'2022-05-08 06:00:58.924','2022-05-08 06:00:58.926',2,NULL,''),('17507','process_5:1:11','17501','17505','input','17508',NULL,'ÂΩïÂÖ•','userTask','admin','2022-05-08 06:00:58.929','2022-05-08 06:57:39.436',3400507,NULL,''),('27501','process_5:1:11','15001','15005','audit','27502',NULL,'ÂÆ°Ê†∏','userTask','admin','2022-05-08 06:57:30.397',NULL,NULL,NULL,''),('27503','process_5:1:11','17501','17505','audit','27504',NULL,'ÂÆ°Ê†∏','userTask','admin','2022-05-08 06:57:39.437',NULL,NULL,NULL,''),('27510','process_5:1:11','27505','27509','start',NULL,NULL,'ÂºÄÂßã','startEvent',NULL,'2022-05-08 06:59:54.767','2022-05-08 06:59:54.767',0,NULL,''),('27511','process_5:1:11','27505','27509','input','27512',NULL,'ÂΩïÂÖ•','userTask','admin','2022-05-08 06:59:54.768','2022-05-08 07:00:01.247',6479,NULL,''),('27514','process_5:1:11','27505','27509','audit','27515',NULL,'ÂÆ°Ê†∏','userTask','admin','2022-05-08 07:00:01.247',NULL,NULL,NULL,''),('30006','process_5:1:11','30001','30005','start',NULL,NULL,'ÂºÄÂßã','startEvent',NULL,'2022-05-08 07:03:55.581','2022-05-08 07:03:55.583',2,NULL,''),('30007','process_5:1:11','30001','30005','input','30008',NULL,'ÂΩïÂÖ•','userTask','admin','2022-05-08 07:03:55.584','2022-05-08 07:04:03.479',7895,NULL,''),('30010','process_5:1:11','30001','30005','audit','30011',NULL,'ÂÆ°Ê†∏','userTask','admin','2022-05-08 07:04:03.479',NULL,NULL,NULL,'');
/*!40000 ALTER TABLE `act_hi_actinst` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `act_hi_attachment`
--

DROP TABLE IF EXISTS `act_hi_attachment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `act_hi_attachment` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `REV_` int(11) DEFAULT NULL,
  `USER_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `NAME_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `DESCRIPTION_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `TYPE_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `TASK_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `PROC_INST_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `URL_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `CONTENT_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `TIME_` datetime(3) DEFAULT NULL,
  PRIMARY KEY (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `act_hi_attachment`
--

LOCK TABLES `act_hi_attachment` WRITE;
/*!40000 ALTER TABLE `act_hi_attachment` DISABLE KEYS */;
/*!40000 ALTER TABLE `act_hi_attachment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `act_hi_comment`
--

DROP TABLE IF EXISTS `act_hi_comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `act_hi_comment` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `TYPE_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `TIME_` datetime(3) NOT NULL,
  `USER_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `TASK_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `PROC_INST_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `ACTION_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `MESSAGE_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `FULL_MSG_` longblob,
  PRIMARY KEY (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `act_hi_comment`
--

LOCK TABLES `act_hi_comment` WRITE;
/*!40000 ALTER TABLE `act_hi_comment` DISABLE KEYS */;
/*!40000 ALTER TABLE `act_hi_comment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `act_hi_detail`
--

DROP TABLE IF EXISTS `act_hi_detail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `act_hi_detail` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `TYPE_` varchar(255) COLLATE utf8_bin NOT NULL,
  `PROC_INST_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `EXECUTION_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `TASK_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `ACT_INST_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `NAME_` varchar(255) COLLATE utf8_bin NOT NULL,
  `VAR_TYPE_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `REV_` int(11) DEFAULT NULL,
  `TIME_` datetime(3) NOT NULL,
  `BYTEARRAY_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `DOUBLE_` double DEFAULT NULL,
  `LONG_` bigint(20) DEFAULT NULL,
  `TEXT_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `TEXT2_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID_`),
  KEY `ACT_IDX_HI_DETAIL_PROC_INST` (`PROC_INST_ID_`),
  KEY `ACT_IDX_HI_DETAIL_ACT_INST` (`ACT_INST_ID_`),
  KEY `ACT_IDX_HI_DETAIL_TIME` (`TIME_`),
  KEY `ACT_IDX_HI_DETAIL_NAME` (`NAME_`),
  KEY `ACT_IDX_HI_DETAIL_TASK_ID` (`TASK_ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `act_hi_detail`
--

LOCK TABLES `act_hi_detail` WRITE;
/*!40000 ALTER TABLE `act_hi_detail` DISABLE KEYS */;
/*!40000 ALTER TABLE `act_hi_detail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `act_hi_identitylink`
--

DROP TABLE IF EXISTS `act_hi_identitylink`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `act_hi_identitylink` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `GROUP_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `TYPE_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `USER_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `TASK_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `PROC_INST_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID_`),
  KEY `ACT_IDX_HI_IDENT_LNK_USER` (`USER_ID_`),
  KEY `ACT_IDX_HI_IDENT_LNK_TASK` (`TASK_ID_`),
  KEY `ACT_IDX_HI_IDENT_LNK_PROCINST` (`PROC_INST_ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `act_hi_identitylink`
--

LOCK TABLES `act_hi_identitylink` WRITE;
/*!40000 ALTER TABLE `act_hi_identitylink` DISABLE KEYS */;
INSERT INTO `act_hi_identitylink` VALUES ('15009',NULL,'participant','admin',NULL,'15001'),('17509',NULL,'participant','admin',NULL,'17501'),('27513',NULL,'participant','admin',NULL,'27505'),('30009',NULL,'participant','admin',NULL,'30001');
/*!40000 ALTER TABLE `act_hi_identitylink` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `act_hi_procinst`
--

DROP TABLE IF EXISTS `act_hi_procinst`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `act_hi_procinst` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `PROC_INST_ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `BUSINESS_KEY_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `PROC_DEF_ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `START_TIME_` datetime(3) NOT NULL,
  `END_TIME_` datetime(3) DEFAULT NULL,
  `DURATION_` bigint(20) DEFAULT NULL,
  `START_USER_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `START_ACT_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `END_ACT_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `SUPER_PROCESS_INSTANCE_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `DELETE_REASON_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `TENANT_ID_` varchar(255) COLLATE utf8_bin DEFAULT '',
  `NAME_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID_`),
  UNIQUE KEY `PROC_INST_ID_` (`PROC_INST_ID_`),
  KEY `ACT_IDX_HI_PRO_INST_END` (`END_TIME_`),
  KEY `ACT_IDX_HI_PRO_I_BUSKEY` (`BUSINESS_KEY_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `act_hi_procinst`
--

LOCK TABLES `act_hi_procinst` WRITE;
/*!40000 ALTER TABLE `act_hi_procinst` DISABLE KEYS */;
INSERT INTO `act_hi_procinst` VALUES ('15001','15001','6','process_5:1:11','2022-05-08 05:38:45.406',NULL,NULL,NULL,'start',NULL,NULL,NULL,'',NULL),('17501','17501','7','process_5:1:11','2022-05-08 06:00:58.814',NULL,NULL,NULL,'start',NULL,NULL,NULL,'',NULL),('27505','27505','8','process_5:1:11','2022-05-08 06:59:54.765',NULL,NULL,NULL,'start',NULL,NULL,NULL,'',NULL),('30001','30001','9','process_5:1:11','2022-05-08 07:03:55.521',NULL,NULL,NULL,'start',NULL,NULL,NULL,'',NULL);
/*!40000 ALTER TABLE `act_hi_procinst` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `act_hi_taskinst`
--

DROP TABLE IF EXISTS `act_hi_taskinst`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `act_hi_taskinst` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `PROC_DEF_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `TASK_DEF_KEY_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `PROC_INST_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `EXECUTION_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `NAME_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `PARENT_TASK_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `DESCRIPTION_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `OWNER_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `ASSIGNEE_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `START_TIME_` datetime(3) NOT NULL,
  `CLAIM_TIME_` datetime(3) DEFAULT NULL,
  `END_TIME_` datetime(3) DEFAULT NULL,
  `DURATION_` bigint(20) DEFAULT NULL,
  `DELETE_REASON_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `PRIORITY_` int(11) DEFAULT NULL,
  `DUE_DATE_` datetime(3) DEFAULT NULL,
  `FORM_KEY_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `CATEGORY_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `TENANT_ID_` varchar(255) COLLATE utf8_bin DEFAULT '',
  PRIMARY KEY (`ID_`),
  KEY `ACT_IDX_HI_TASK_INST_PROCINST` (`PROC_INST_ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `act_hi_taskinst`
--

LOCK TABLES `act_hi_taskinst` WRITE;
/*!40000 ALTER TABLE `act_hi_taskinst` DISABLE KEYS */;
INSERT INTO `act_hi_taskinst` VALUES ('15008','process_5:1:11','input','15001','15005','ÂΩïÂÖ•',NULL,'000',NULL,'admin','2022-05-08 05:38:45.455',NULL,'2022-05-08 06:57:30.331',4724876,NULL,50,NULL,NULL,NULL,''),('17508','process_5:1:11','input','17501','17505','ÂΩïÂÖ•',NULL,'000',NULL,'admin','2022-05-08 06:00:58.943',NULL,'2022-05-08 06:57:39.432',3400489,NULL,50,NULL,NULL,NULL,''),('27502','process_5:1:11','audit','15001','15005','ÂÆ°Ê†∏',NULL,'011',NULL,'admin','2022-05-08 06:57:30.398',NULL,NULL,NULL,NULL,50,NULL,NULL,NULL,''),('27504','process_5:1:11','audit','17501','17505','ÂÆ°Ê†∏',NULL,'011',NULL,'admin','2022-05-08 06:57:39.437',NULL,NULL,NULL,NULL,50,NULL,NULL,NULL,''),('27512','process_5:1:11','input','27505','27509','ÂΩïÂÖ•',NULL,'000',NULL,'admin','2022-05-08 06:59:54.768',NULL,'2022-05-08 07:00:01.241',6473,NULL,50,NULL,NULL,NULL,''),('27515','process_5:1:11','audit','27505','27509','ÂÆ°Ê†∏',NULL,'011',NULL,'admin','2022-05-08 07:00:01.247',NULL,NULL,NULL,NULL,50,NULL,NULL,NULL,''),('30008','process_5:1:11','input','30001','30005','ÂΩïÂÖ•',NULL,'000',NULL,'admin','2022-05-08 07:03:55.598',NULL,'2022-05-08 07:04:03.472',7874,NULL,50,NULL,NULL,NULL,''),('30011','process_5:1:11','audit','30001','30005','ÂÆ°Ê†∏',NULL,'011',NULL,'admin','2022-05-08 07:04:03.480',NULL,NULL,NULL,NULL,50,NULL,NULL,NULL,'');
/*!40000 ALTER TABLE `act_hi_taskinst` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `act_hi_varinst`
--

DROP TABLE IF EXISTS `act_hi_varinst`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `act_hi_varinst` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `PROC_INST_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `EXECUTION_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `TASK_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `NAME_` varchar(255) COLLATE utf8_bin NOT NULL,
  `VAR_TYPE_` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `REV_` int(11) DEFAULT NULL,
  `BYTEARRAY_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `DOUBLE_` double DEFAULT NULL,
  `LONG_` bigint(20) DEFAULT NULL,
  `TEXT_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `TEXT2_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `CREATE_TIME_` datetime(3) DEFAULT NULL,
  `LAST_UPDATED_TIME_` datetime(3) DEFAULT NULL,
  PRIMARY KEY (`ID_`),
  KEY `ACT_IDX_HI_PROCVAR_PROC_INST` (`PROC_INST_ID_`),
  KEY `ACT_IDX_HI_PROCVAR_NAME_TYPE` (`NAME_`,`VAR_TYPE_`),
  KEY `ACT_IDX_HI_PROCVAR_TASK_ID` (`TASK_ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `act_hi_varinst`
--

LOCK TABLES `act_hi_varinst` WRITE;
/*!40000 ALTER TABLE `act_hi_varinst` DISABLE KEYS */;
INSERT INTO `act_hi_varinst` VALUES ('15002','15001','15001',NULL,'processDefinitionId','string',0,NULL,NULL,NULL,'process_5:1:11',NULL,'2022-05-08 05:38:45.453','2022-05-08 05:38:45.453'),('15003','15001','15001',NULL,'businessKey','long',0,NULL,NULL,6,'6',NULL,'2022-05-08 05:38:45.454','2022-05-08 05:38:45.454'),('15004','15001','15001',NULL,'userid','string',0,NULL,NULL,NULL,'admin',NULL,'2022-05-08 05:38:45.454','2022-05-08 05:38:45.454'),('17502','17501','17501',NULL,'processDefinitionId','string',0,NULL,NULL,NULL,'process_5:1:11',NULL,'2022-05-08 06:00:58.920','2022-05-08 06:00:58.920'),('17503','17501','17501',NULL,'businessKey','long',0,NULL,NULL,7,'7',NULL,'2022-05-08 06:00:58.921','2022-05-08 06:00:58.921'),('17504','17501','17501',NULL,'userid','string',0,NULL,NULL,NULL,'admin',NULL,'2022-05-08 06:00:58.922','2022-05-08 06:00:58.922'),('27506','27505','27505',NULL,'processDefinitionId','string',0,NULL,NULL,NULL,'process_5:1:11',NULL,'2022-05-08 06:59:54.766','2022-05-08 06:59:54.766'),('27507','27505','27505',NULL,'businessKey','long',0,NULL,NULL,8,'8',NULL,'2022-05-08 06:59:54.767','2022-05-08 06:59:54.767'),('27508','27505','27505',NULL,'userid','string',0,NULL,NULL,NULL,'admin',NULL,'2022-05-08 06:59:54.767','2022-05-08 06:59:54.767'),('30002','30001','30001',NULL,'processDefinitionId','string',0,NULL,NULL,NULL,'process_5:1:11',NULL,'2022-05-08 07:03:55.577','2022-05-08 07:03:55.577'),('30003','30001','30001',NULL,'businessKey','long',0,NULL,NULL,9,'9',NULL,'2022-05-08 07:03:55.578','2022-05-08 07:03:55.578'),('30004','30001','30001',NULL,'userid','string',0,NULL,NULL,NULL,'admin',NULL,'2022-05-08 07:03:55.578','2022-05-08 07:03:55.578');
/*!40000 ALTER TABLE `act_hi_varinst` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `act_id_group`
--

DROP TABLE IF EXISTS `act_id_group`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `act_id_group` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `REV_` int(11) DEFAULT NULL,
  `NAME_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `TYPE_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `act_id_group`
--

LOCK TABLES `act_id_group` WRITE;
/*!40000 ALTER TABLE `act_id_group` DISABLE KEYS */;
/*!40000 ALTER TABLE `act_id_group` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `act_id_info`
--

DROP TABLE IF EXISTS `act_id_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `act_id_info` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `REV_` int(11) DEFAULT NULL,
  `USER_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `TYPE_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `KEY_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `VALUE_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `PASSWORD_` longblob,
  `PARENT_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `act_id_info`
--

LOCK TABLES `act_id_info` WRITE;
/*!40000 ALTER TABLE `act_id_info` DISABLE KEYS */;
/*!40000 ALTER TABLE `act_id_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `act_id_membership`
--

DROP TABLE IF EXISTS `act_id_membership`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `act_id_membership` (
  `USER_ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `GROUP_ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`USER_ID_`,`GROUP_ID_`),
  KEY `ACT_FK_MEMB_GROUP` (`GROUP_ID_`),
  CONSTRAINT `ACT_FK_MEMB_GROUP` FOREIGN KEY (`GROUP_ID_`) REFERENCES `act_id_group` (`ID_`),
  CONSTRAINT `ACT_FK_MEMB_USER` FOREIGN KEY (`USER_ID_`) REFERENCES `act_id_user` (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `act_id_membership`
--

LOCK TABLES `act_id_membership` WRITE;
/*!40000 ALTER TABLE `act_id_membership` DISABLE KEYS */;
/*!40000 ALTER TABLE `act_id_membership` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `act_id_user`
--

DROP TABLE IF EXISTS `act_id_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `act_id_user` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `REV_` int(11) DEFAULT NULL,
  `FIRST_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `LAST_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `EMAIL_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `PWD_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `PICTURE_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `act_id_user`
--

LOCK TABLES `act_id_user` WRITE;
/*!40000 ALTER TABLE `act_id_user` DISABLE KEYS */;
/*!40000 ALTER TABLE `act_id_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `act_procdef_info`
--

DROP TABLE IF EXISTS `act_procdef_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `act_procdef_info` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `PROC_DEF_ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `REV_` int(11) DEFAULT NULL,
  `INFO_JSON_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID_`),
  UNIQUE KEY `ACT_UNIQ_INFO_PROCDEF` (`PROC_DEF_ID_`),
  KEY `ACT_IDX_INFO_PROCDEF` (`PROC_DEF_ID_`),
  KEY `ACT_FK_INFO_JSON_BA` (`INFO_JSON_ID_`),
  CONSTRAINT `ACT_FK_INFO_JSON_BA` FOREIGN KEY (`INFO_JSON_ID_`) REFERENCES `act_ge_bytearray` (`ID_`),
  CONSTRAINT `ACT_FK_INFO_PROCDEF` FOREIGN KEY (`PROC_DEF_ID_`) REFERENCES `act_re_procdef` (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `act_procdef_info`
--

LOCK TABLES `act_procdef_info` WRITE;
/*!40000 ALTER TABLE `act_procdef_info` DISABLE KEYS */;
/*!40000 ALTER TABLE `act_procdef_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `act_re_deployment`
--

DROP TABLE IF EXISTS `act_re_deployment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `act_re_deployment` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `NAME_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `CATEGORY_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `KEY_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `TENANT_ID_` varchar(255) COLLATE utf8_bin DEFAULT '',
  `DEPLOY_TIME_` timestamp(3) NULL DEFAULT NULL,
  `ENGINE_VERSION_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `act_re_deployment`
--

LOCK TABLES `act_re_deployment` WRITE;
/*!40000 ALTER TABLE `act_re_deployment` DISABLE KEYS */;
INSERT INTO `act_re_deployment` VALUES ('8','ËØ∑ÂÅáÊµÅÁ®ã',NULL,NULL,'','2022-05-07 20:58:10.671',NULL);
/*!40000 ALTER TABLE `act_re_deployment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `act_re_model`
--

DROP TABLE IF EXISTS `act_re_model`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `act_re_model` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `REV_` int(11) DEFAULT NULL,
  `NAME_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `KEY_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `CATEGORY_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `CREATE_TIME_` timestamp(3) NULL DEFAULT NULL,
  `LAST_UPDATE_TIME_` timestamp(3) NULL DEFAULT NULL,
  `VERSION_` int(11) DEFAULT NULL,
  `META_INFO_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `DEPLOYMENT_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `EDITOR_SOURCE_VALUE_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `EDITOR_SOURCE_EXTRA_VALUE_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `TENANT_ID_` varchar(255) COLLATE utf8_bin DEFAULT '',
  PRIMARY KEY (`ID_`),
  KEY `ACT_FK_MODEL_SOURCE` (`EDITOR_SOURCE_VALUE_ID_`),
  KEY `ACT_FK_MODEL_SOURCE_EXTRA` (`EDITOR_SOURCE_EXTRA_VALUE_ID_`),
  KEY `ACT_FK_MODEL_DEPLOYMENT` (`DEPLOYMENT_ID_`),
  CONSTRAINT `ACT_FK_MODEL_DEPLOYMENT` FOREIGN KEY (`DEPLOYMENT_ID_`) REFERENCES `act_re_deployment` (`ID_`),
  CONSTRAINT `ACT_FK_MODEL_SOURCE` FOREIGN KEY (`EDITOR_SOURCE_VALUE_ID_`) REFERENCES `act_ge_bytearray` (`ID_`),
  CONSTRAINT `ACT_FK_MODEL_SOURCE_EXTRA` FOREIGN KEY (`EDITOR_SOURCE_EXTRA_VALUE_ID_`) REFERENCES `act_ge_bytearray` (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `act_re_model`
--

LOCK TABLES `act_re_model` WRITE;
/*!40000 ALTER TABLE `act_re_model` DISABLE KEYS */;
INSERT INTO `act_re_model` VALUES ('1',8,'ËØ∑ÂÅáÊµÅÁ®ã','ËØ∑ÂÅáÊµÅÁ®ã_1',NULL,'2022-05-07 20:41:10.047','2022-05-07 20:58:11.083',1,'{\"name\":\"ËØ∑ÂÅáÊµÅÁ®ã\",\"revision\":1,\"description\":\"ËøôÊòØ‰∏Ä‰∏™ËØ∑ÂÅáÊµÅÁ®ã\"}','8','2','3','');
/*!40000 ALTER TABLE `act_re_model` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `act_re_procdef`
--

DROP TABLE IF EXISTS `act_re_procdef`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `act_re_procdef` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `REV_` int(11) DEFAULT NULL,
  `CATEGORY_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `NAME_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `KEY_` varchar(255) COLLATE utf8_bin NOT NULL,
  `VERSION_` int(11) NOT NULL,
  `DEPLOYMENT_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `RESOURCE_NAME_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `DGRM_RESOURCE_NAME_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `DESCRIPTION_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `HAS_START_FORM_KEY_` tinyint(4) DEFAULT NULL,
  `HAS_GRAPHICAL_NOTATION_` tinyint(4) DEFAULT NULL,
  `SUSPENSION_STATE_` int(11) DEFAULT NULL,
  `TENANT_ID_` varchar(255) COLLATE utf8_bin DEFAULT '',
  `ENGINE_VERSION_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID_`),
  UNIQUE KEY `ACT_UNIQ_PROCDEF` (`KEY_`,`VERSION_`,`TENANT_ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `act_re_procdef`
--

LOCK TABLES `act_re_procdef` WRITE;
/*!40000 ALTER TABLE `act_re_procdef` DISABLE KEYS */;
INSERT INTO `act_re_procdef` VALUES ('process_5:1:11',1,'http://www.activiti.org/processdef',NULL,'process_5',1,'8','ËØ∑ÂÅáÊµÅÁ®ã.bpmn20.xml','ËØ∑ÂÅáÊµÅÁ®ã.process_5.png',NULL,0,1,1,'',NULL);
/*!40000 ALTER TABLE `act_re_procdef` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `act_ru_deadletter_job`
--

DROP TABLE IF EXISTS `act_ru_deadletter_job`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `act_ru_deadletter_job` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `REV_` int(11) DEFAULT NULL,
  `TYPE_` varchar(255) COLLATE utf8_bin NOT NULL,
  `EXCLUSIVE_` tinyint(1) DEFAULT NULL,
  `EXECUTION_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `PROCESS_INSTANCE_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `PROC_DEF_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `EXCEPTION_STACK_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `EXCEPTION_MSG_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `DUEDATE_` timestamp(3) NULL DEFAULT NULL,
  `REPEAT_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `HANDLER_TYPE_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `HANDLER_CFG_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `TENANT_ID_` varchar(255) COLLATE utf8_bin DEFAULT '',
  PRIMARY KEY (`ID_`),
  KEY `ACT_FK_DEADLETTER_JOB_EXECUTION` (`EXECUTION_ID_`),
  KEY `ACT_FK_DEADLETTER_JOB_PROCESS_INSTANCE` (`PROCESS_INSTANCE_ID_`),
  KEY `ACT_FK_DEADLETTER_JOB_PROC_DEF` (`PROC_DEF_ID_`),
  KEY `ACT_FK_DEADLETTER_JOB_EXCEPTION` (`EXCEPTION_STACK_ID_`),
  CONSTRAINT `ACT_FK_DEADLETTER_JOB_EXCEPTION` FOREIGN KEY (`EXCEPTION_STACK_ID_`) REFERENCES `act_ge_bytearray` (`ID_`),
  CONSTRAINT `ACT_FK_DEADLETTER_JOB_EXECUTION` FOREIGN KEY (`EXECUTION_ID_`) REFERENCES `act_ru_execution` (`ID_`),
  CONSTRAINT `ACT_FK_DEADLETTER_JOB_PROCESS_INSTANCE` FOREIGN KEY (`PROCESS_INSTANCE_ID_`) REFERENCES `act_ru_execution` (`ID_`),
  CONSTRAINT `ACT_FK_DEADLETTER_JOB_PROC_DEF` FOREIGN KEY (`PROC_DEF_ID_`) REFERENCES `act_re_procdef` (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `act_ru_deadletter_job`
--

LOCK TABLES `act_ru_deadletter_job` WRITE;
/*!40000 ALTER TABLE `act_ru_deadletter_job` DISABLE KEYS */;
/*!40000 ALTER TABLE `act_ru_deadletter_job` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `act_ru_event_subscr`
--

DROP TABLE IF EXISTS `act_ru_event_subscr`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `act_ru_event_subscr` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `REV_` int(11) DEFAULT NULL,
  `EVENT_TYPE_` varchar(255) COLLATE utf8_bin NOT NULL,
  `EVENT_NAME_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `EXECUTION_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `PROC_INST_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `ACTIVITY_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `CONFIGURATION_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `CREATED_` timestamp(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3),
  `PROC_DEF_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `TENANT_ID_` varchar(255) COLLATE utf8_bin DEFAULT '',
  PRIMARY KEY (`ID_`),
  KEY `ACT_IDX_EVENT_SUBSCR_CONFIG_` (`CONFIGURATION_`),
  KEY `ACT_FK_EVENT_EXEC` (`EXECUTION_ID_`),
  CONSTRAINT `ACT_FK_EVENT_EXEC` FOREIGN KEY (`EXECUTION_ID_`) REFERENCES `act_ru_execution` (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `act_ru_event_subscr`
--

LOCK TABLES `act_ru_event_subscr` WRITE;
/*!40000 ALTER TABLE `act_ru_event_subscr` DISABLE KEYS */;
/*!40000 ALTER TABLE `act_ru_event_subscr` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `act_ru_execution`
--

DROP TABLE IF EXISTS `act_ru_execution`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `act_ru_execution` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `REV_` int(11) DEFAULT NULL,
  `PROC_INST_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `BUSINESS_KEY_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `PARENT_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `PROC_DEF_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `SUPER_EXEC_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `ROOT_PROC_INST_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `ACT_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `IS_ACTIVE_` tinyint(4) DEFAULT NULL,
  `IS_CONCURRENT_` tinyint(4) DEFAULT NULL,
  `IS_SCOPE_` tinyint(4) DEFAULT NULL,
  `IS_EVENT_SCOPE_` tinyint(4) DEFAULT NULL,
  `IS_MI_ROOT_` tinyint(4) DEFAULT NULL,
  `SUSPENSION_STATE_` int(11) DEFAULT NULL,
  `CACHED_ENT_STATE_` int(11) DEFAULT NULL,
  `TENANT_ID_` varchar(255) COLLATE utf8_bin DEFAULT '',
  `NAME_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `START_TIME_` datetime(3) DEFAULT NULL,
  `START_USER_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `LOCK_TIME_` timestamp(3) NULL DEFAULT NULL,
  `IS_COUNT_ENABLED_` tinyint(4) DEFAULT NULL,
  `EVT_SUBSCR_COUNT_` int(11) DEFAULT NULL,
  `TASK_COUNT_` int(11) DEFAULT NULL,
  `JOB_COUNT_` int(11) DEFAULT NULL,
  `TIMER_JOB_COUNT_` int(11) DEFAULT NULL,
  `SUSP_JOB_COUNT_` int(11) DEFAULT NULL,
  `DEADLETTER_JOB_COUNT_` int(11) DEFAULT NULL,
  `VAR_COUNT_` int(11) DEFAULT NULL,
  `ID_LINK_COUNT_` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID_`),
  KEY `ACT_IDX_EXEC_BUSKEY` (`BUSINESS_KEY_`),
  KEY `ACT_IDC_EXEC_ROOT` (`ROOT_PROC_INST_ID_`),
  KEY `ACT_FK_EXE_PROCINST` (`PROC_INST_ID_`),
  KEY `ACT_FK_EXE_PARENT` (`PARENT_ID_`),
  KEY `ACT_FK_EXE_SUPER` (`SUPER_EXEC_`),
  KEY `ACT_FK_EXE_PROCDEF` (`PROC_DEF_ID_`),
  CONSTRAINT `ACT_FK_EXE_PARENT` FOREIGN KEY (`PARENT_ID_`) REFERENCES `act_ru_execution` (`ID_`) ON DELETE CASCADE,
  CONSTRAINT `ACT_FK_EXE_PROCDEF` FOREIGN KEY (`PROC_DEF_ID_`) REFERENCES `act_re_procdef` (`ID_`),
  CONSTRAINT `ACT_FK_EXE_PROCINST` FOREIGN KEY (`PROC_INST_ID_`) REFERENCES `act_ru_execution` (`ID_`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `ACT_FK_EXE_SUPER` FOREIGN KEY (`SUPER_EXEC_`) REFERENCES `act_ru_execution` (`ID_`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `act_ru_execution`
--

LOCK TABLES `act_ru_execution` WRITE;
/*!40000 ALTER TABLE `act_ru_execution` DISABLE KEYS */;
INSERT INTO `act_ru_execution` VALUES ('15001',1,'15001','6',NULL,'process_5:1:11',NULL,'15001',NULL,1,0,1,0,0,1,NULL,'',NULL,'2022-05-08 05:38:45.406',NULL,NULL,0,0,0,0,0,0,0,0,0),('15005',2,'15001',NULL,'15001','process_5:1:11',NULL,'15001','audit',1,0,0,0,0,1,NULL,'',NULL,'2022-05-08 05:38:45.454',NULL,NULL,0,0,0,0,0,0,0,0,0),('17501',1,'17501','7',NULL,'process_5:1:11',NULL,'17501',NULL,1,0,1,0,0,1,NULL,'',NULL,'2022-05-08 06:00:58.814',NULL,NULL,0,0,0,0,0,0,0,0,0),('17505',2,'17501',NULL,'17501','process_5:1:11',NULL,'17501','audit',1,0,0,0,0,1,NULL,'',NULL,'2022-05-08 06:00:58.922',NULL,NULL,0,0,0,0,0,0,0,0,0),('27505',1,'27505','8',NULL,'process_5:1:11',NULL,'27505',NULL,1,0,1,0,0,1,NULL,'',NULL,'2022-05-08 06:59:54.765',NULL,NULL,0,0,0,0,0,0,0,0,0),('27509',2,'27505',NULL,'27505','process_5:1:11',NULL,'27505','audit',1,0,0,0,0,1,NULL,'',NULL,'2022-05-08 06:59:54.767',NULL,NULL,0,0,0,0,0,0,0,0,0),('30001',1,'30001','9',NULL,'process_5:1:11',NULL,'30001',NULL,1,0,1,0,0,1,NULL,'',NULL,'2022-05-08 07:03:55.521',NULL,NULL,0,0,0,0,0,0,0,0,0),('30005',2,'30001',NULL,'30001','process_5:1:11',NULL,'30001','audit',1,0,0,0,0,1,NULL,'',NULL,'2022-05-08 07:03:55.578',NULL,NULL,0,0,0,0,0,0,0,0,0);
/*!40000 ALTER TABLE `act_ru_execution` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `act_ru_identitylink`
--

DROP TABLE IF EXISTS `act_ru_identitylink`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `act_ru_identitylink` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `REV_` int(11) DEFAULT NULL,
  `GROUP_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `TYPE_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `USER_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `TASK_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `PROC_INST_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `PROC_DEF_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID_`),
  KEY `ACT_IDX_IDENT_LNK_USER` (`USER_ID_`),
  KEY `ACT_IDX_IDENT_LNK_GROUP` (`GROUP_ID_`),
  KEY `ACT_IDX_ATHRZ_PROCEDEF` (`PROC_DEF_ID_`),
  KEY `ACT_FK_TSKASS_TASK` (`TASK_ID_`),
  KEY `ACT_FK_IDL_PROCINST` (`PROC_INST_ID_`),
  CONSTRAINT `ACT_FK_ATHRZ_PROCEDEF` FOREIGN KEY (`PROC_DEF_ID_`) REFERENCES `act_re_procdef` (`ID_`),
  CONSTRAINT `ACT_FK_IDL_PROCINST` FOREIGN KEY (`PROC_INST_ID_`) REFERENCES `act_ru_execution` (`ID_`),
  CONSTRAINT `ACT_FK_TSKASS_TASK` FOREIGN KEY (`TASK_ID_`) REFERENCES `act_ru_task` (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `act_ru_identitylink`
--

LOCK TABLES `act_ru_identitylink` WRITE;
/*!40000 ALTER TABLE `act_ru_identitylink` DISABLE KEYS */;
INSERT INTO `act_ru_identitylink` VALUES ('15009',1,NULL,'participant','admin',NULL,'15001',NULL),('17509',1,NULL,'participant','admin',NULL,'17501',NULL),('27513',1,NULL,'participant','admin',NULL,'27505',NULL),('30009',1,NULL,'participant','admin',NULL,'30001',NULL);
/*!40000 ALTER TABLE `act_ru_identitylink` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `act_ru_job`
--

DROP TABLE IF EXISTS `act_ru_job`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `act_ru_job` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `REV_` int(11) DEFAULT NULL,
  `TYPE_` varchar(255) COLLATE utf8_bin NOT NULL,
  `LOCK_EXP_TIME_` timestamp(3) NULL DEFAULT NULL,
  `LOCK_OWNER_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `EXCLUSIVE_` tinyint(1) DEFAULT NULL,
  `EXECUTION_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `PROCESS_INSTANCE_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `PROC_DEF_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `RETRIES_` int(11) DEFAULT NULL,
  `EXCEPTION_STACK_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `EXCEPTION_MSG_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `DUEDATE_` timestamp(3) NULL DEFAULT NULL,
  `REPEAT_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `HANDLER_TYPE_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `HANDLER_CFG_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `TENANT_ID_` varchar(255) COLLATE utf8_bin DEFAULT '',
  PRIMARY KEY (`ID_`),
  KEY `ACT_FK_JOB_EXECUTION` (`EXECUTION_ID_`),
  KEY `ACT_FK_JOB_PROCESS_INSTANCE` (`PROCESS_INSTANCE_ID_`),
  KEY `ACT_FK_JOB_PROC_DEF` (`PROC_DEF_ID_`),
  KEY `ACT_FK_JOB_EXCEPTION` (`EXCEPTION_STACK_ID_`),
  CONSTRAINT `ACT_FK_JOB_EXCEPTION` FOREIGN KEY (`EXCEPTION_STACK_ID_`) REFERENCES `act_ge_bytearray` (`ID_`),
  CONSTRAINT `ACT_FK_JOB_EXECUTION` FOREIGN KEY (`EXECUTION_ID_`) REFERENCES `act_ru_execution` (`ID_`),
  CONSTRAINT `ACT_FK_JOB_PROCESS_INSTANCE` FOREIGN KEY (`PROCESS_INSTANCE_ID_`) REFERENCES `act_ru_execution` (`ID_`),
  CONSTRAINT `ACT_FK_JOB_PROC_DEF` FOREIGN KEY (`PROC_DEF_ID_`) REFERENCES `act_re_procdef` (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `act_ru_job`
--

LOCK TABLES `act_ru_job` WRITE;
/*!40000 ALTER TABLE `act_ru_job` DISABLE KEYS */;
/*!40000 ALTER TABLE `act_ru_job` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `act_ru_suspended_job`
--

DROP TABLE IF EXISTS `act_ru_suspended_job`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `act_ru_suspended_job` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `REV_` int(11) DEFAULT NULL,
  `TYPE_` varchar(255) COLLATE utf8_bin NOT NULL,
  `EXCLUSIVE_` tinyint(1) DEFAULT NULL,
  `EXECUTION_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `PROCESS_INSTANCE_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `PROC_DEF_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `RETRIES_` int(11) DEFAULT NULL,
  `EXCEPTION_STACK_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `EXCEPTION_MSG_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `DUEDATE_` timestamp(3) NULL DEFAULT NULL,
  `REPEAT_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `HANDLER_TYPE_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `HANDLER_CFG_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `TENANT_ID_` varchar(255) COLLATE utf8_bin DEFAULT '',
  PRIMARY KEY (`ID_`),
  KEY `ACT_FK_SUSPENDED_JOB_EXECUTION` (`EXECUTION_ID_`),
  KEY `ACT_FK_SUSPENDED_JOB_PROCESS_INSTANCE` (`PROCESS_INSTANCE_ID_`),
  KEY `ACT_FK_SUSPENDED_JOB_PROC_DEF` (`PROC_DEF_ID_`),
  KEY `ACT_FK_SUSPENDED_JOB_EXCEPTION` (`EXCEPTION_STACK_ID_`),
  CONSTRAINT `ACT_FK_SUSPENDED_JOB_EXCEPTION` FOREIGN KEY (`EXCEPTION_STACK_ID_`) REFERENCES `act_ge_bytearray` (`ID_`),
  CONSTRAINT `ACT_FK_SUSPENDED_JOB_EXECUTION` FOREIGN KEY (`EXECUTION_ID_`) REFERENCES `act_ru_execution` (`ID_`),
  CONSTRAINT `ACT_FK_SUSPENDED_JOB_PROCESS_INSTANCE` FOREIGN KEY (`PROCESS_INSTANCE_ID_`) REFERENCES `act_ru_execution` (`ID_`),
  CONSTRAINT `ACT_FK_SUSPENDED_JOB_PROC_DEF` FOREIGN KEY (`PROC_DEF_ID_`) REFERENCES `act_re_procdef` (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `act_ru_suspended_job`
--

LOCK TABLES `act_ru_suspended_job` WRITE;
/*!40000 ALTER TABLE `act_ru_suspended_job` DISABLE KEYS */;
/*!40000 ALTER TABLE `act_ru_suspended_job` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `act_ru_task`
--

DROP TABLE IF EXISTS `act_ru_task`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `act_ru_task` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `REV_` int(11) DEFAULT NULL,
  `EXECUTION_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `PROC_INST_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `PROC_DEF_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `NAME_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `PARENT_TASK_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `DESCRIPTION_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `TASK_DEF_KEY_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `OWNER_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `ASSIGNEE_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `DELEGATION_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `PRIORITY_` int(11) DEFAULT NULL,
  `CREATE_TIME_` timestamp(3) NULL DEFAULT NULL,
  `DUE_DATE_` datetime(3) DEFAULT NULL,
  `CATEGORY_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `SUSPENSION_STATE_` int(11) DEFAULT NULL,
  `TENANT_ID_` varchar(255) COLLATE utf8_bin DEFAULT '',
  `FORM_KEY_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `CLAIM_TIME_` datetime(3) DEFAULT NULL,
  PRIMARY KEY (`ID_`),
  KEY `ACT_IDX_TASK_CREATE` (`CREATE_TIME_`),
  KEY `ACT_FK_TASK_EXE` (`EXECUTION_ID_`),
  KEY `ACT_FK_TASK_PROCINST` (`PROC_INST_ID_`),
  KEY `ACT_FK_TASK_PROCDEF` (`PROC_DEF_ID_`),
  CONSTRAINT `ACT_FK_TASK_EXE` FOREIGN KEY (`EXECUTION_ID_`) REFERENCES `act_ru_execution` (`ID_`),
  CONSTRAINT `ACT_FK_TASK_PROCDEF` FOREIGN KEY (`PROC_DEF_ID_`) REFERENCES `act_re_procdef` (`ID_`),
  CONSTRAINT `ACT_FK_TASK_PROCINST` FOREIGN KEY (`PROC_INST_ID_`) REFERENCES `act_ru_execution` (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `act_ru_task`
--

LOCK TABLES `act_ru_task` WRITE;
/*!40000 ALTER TABLE `act_ru_task` DISABLE KEYS */;
INSERT INTO `act_ru_task` VALUES ('27502',1,'15005','15001','process_5:1:11','ÂÆ°Ê†∏',NULL,'011','audit',NULL,'admin',NULL,50,'2022-05-07 22:57:30.397',NULL,NULL,1,'',NULL,NULL),('27504',1,'17505','17501','process_5:1:11','ÂÆ°Ê†∏',NULL,'011','audit',NULL,'admin',NULL,50,'2022-05-07 22:57:39.437',NULL,NULL,1,'',NULL,NULL),('27515',1,'27509','27505','process_5:1:11','ÂÆ°Ê†∏',NULL,'011','audit',NULL,'admin',NULL,50,'2022-05-07 23:00:01.247',NULL,NULL,1,'',NULL,NULL),('30011',1,'30005','30001','process_5:1:11','ÂÆ°Ê†∏',NULL,'011','audit',NULL,'admin',NULL,50,'2022-05-07 23:04:03.479',NULL,NULL,1,'',NULL,NULL);
/*!40000 ALTER TABLE `act_ru_task` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `act_ru_timer_job`
--

DROP TABLE IF EXISTS `act_ru_timer_job`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `act_ru_timer_job` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `REV_` int(11) DEFAULT NULL,
  `TYPE_` varchar(255) COLLATE utf8_bin NOT NULL,
  `LOCK_EXP_TIME_` timestamp(3) NULL DEFAULT NULL,
  `LOCK_OWNER_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `EXCLUSIVE_` tinyint(1) DEFAULT NULL,
  `EXECUTION_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `PROCESS_INSTANCE_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `PROC_DEF_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `RETRIES_` int(11) DEFAULT NULL,
  `EXCEPTION_STACK_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `EXCEPTION_MSG_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `DUEDATE_` timestamp(3) NULL DEFAULT NULL,
  `REPEAT_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `HANDLER_TYPE_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `HANDLER_CFG_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `TENANT_ID_` varchar(255) COLLATE utf8_bin DEFAULT '',
  PRIMARY KEY (`ID_`),
  KEY `ACT_FK_TIMER_JOB_EXECUTION` (`EXECUTION_ID_`),
  KEY `ACT_FK_TIMER_JOB_PROCESS_INSTANCE` (`PROCESS_INSTANCE_ID_`),
  KEY `ACT_FK_TIMER_JOB_PROC_DEF` (`PROC_DEF_ID_`),
  KEY `ACT_FK_TIMER_JOB_EXCEPTION` (`EXCEPTION_STACK_ID_`),
  CONSTRAINT `ACT_FK_TIMER_JOB_EXCEPTION` FOREIGN KEY (`EXCEPTION_STACK_ID_`) REFERENCES `act_ge_bytearray` (`ID_`),
  CONSTRAINT `ACT_FK_TIMER_JOB_EXECUTION` FOREIGN KEY (`EXECUTION_ID_`) REFERENCES `act_ru_execution` (`ID_`),
  CONSTRAINT `ACT_FK_TIMER_JOB_PROCESS_INSTANCE` FOREIGN KEY (`PROCESS_INSTANCE_ID_`) REFERENCES `act_ru_execution` (`ID_`),
  CONSTRAINT `ACT_FK_TIMER_JOB_PROC_DEF` FOREIGN KEY (`PROC_DEF_ID_`) REFERENCES `act_re_procdef` (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `act_ru_timer_job`
--

LOCK TABLES `act_ru_timer_job` WRITE;
/*!40000 ALTER TABLE `act_ru_timer_job` DISABLE KEYS */;
/*!40000 ALTER TABLE `act_ru_timer_job` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `act_ru_variable`
--

DROP TABLE IF EXISTS `act_ru_variable`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `act_ru_variable` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `REV_` int(11) DEFAULT NULL,
  `TYPE_` varchar(255) COLLATE utf8_bin NOT NULL,
  `NAME_` varchar(255) COLLATE utf8_bin NOT NULL,
  `EXECUTION_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `PROC_INST_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `TASK_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `BYTEARRAY_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `DOUBLE_` double DEFAULT NULL,
  `LONG_` bigint(20) DEFAULT NULL,
  `TEXT_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `TEXT2_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID_`),
  KEY `ACT_IDX_VARIABLE_TASK_ID` (`TASK_ID_`),
  KEY `ACT_FK_VAR_EXE` (`EXECUTION_ID_`),
  KEY `ACT_FK_VAR_PROCINST` (`PROC_INST_ID_`),
  KEY `ACT_FK_VAR_BYTEARRAY` (`BYTEARRAY_ID_`),
  CONSTRAINT `ACT_FK_VAR_BYTEARRAY` FOREIGN KEY (`BYTEARRAY_ID_`) REFERENCES `act_ge_bytearray` (`ID_`),
  CONSTRAINT `ACT_FK_VAR_EXE` FOREIGN KEY (`EXECUTION_ID_`) REFERENCES `act_ru_execution` (`ID_`),
  CONSTRAINT `ACT_FK_VAR_PROCINST` FOREIGN KEY (`PROC_INST_ID_`) REFERENCES `act_ru_execution` (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `act_ru_variable`
--

LOCK TABLES `act_ru_variable` WRITE;
/*!40000 ALTER TABLE `act_ru_variable` DISABLE KEYS */;
INSERT INTO `act_ru_variable` VALUES ('15002',1,'string','processDefinitionId','15001','15001',NULL,NULL,NULL,NULL,'process_5:1:11',NULL),('15003',1,'long','businessKey','15001','15001',NULL,NULL,NULL,6,'6',NULL),('15004',1,'string','userid','15001','15001',NULL,NULL,NULL,NULL,'admin',NULL),('17502',1,'string','processDefinitionId','17501','17501',NULL,NULL,NULL,NULL,'process_5:1:11',NULL),('17503',1,'long','businessKey','17501','17501',NULL,NULL,NULL,7,'7',NULL),('17504',1,'string','userid','17501','17501',NULL,NULL,NULL,NULL,'admin',NULL),('27506',1,'string','processDefinitionId','27505','27505',NULL,NULL,NULL,NULL,'process_5:1:11',NULL),('27507',1,'long','businessKey','27505','27505',NULL,NULL,NULL,8,'8',NULL),('27508',1,'string','userid','27505','27505',NULL,NULL,NULL,NULL,'admin',NULL),('30002',1,'string','processDefinitionId','30001','30001',NULL,NULL,NULL,NULL,'process_5:1:11',NULL),('30003',1,'long','businessKey','30001','30001',NULL,NULL,NULL,9,'9',NULL),('30004',1,'string','userid','30001','30001',NULL,NULL,NULL,NULL,'admin',NULL);
/*!40000 ALTER TABLE `act_ru_variable` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `databasechangelog`
--

DROP TABLE IF EXISTS `databasechangelog`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `databasechangelog` (
  `ID` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `AUTHOR` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `FILENAME` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `DATEEXECUTED` datetime NOT NULL,
  `ORDEREXECUTED` int(11) NOT NULL,
  `EXECTYPE` varchar(10) COLLATE utf8mb4_unicode_ci NOT NULL,
  `MD5SUM` varchar(35) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `DESCRIPTION` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `COMMENTS` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `TAG` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `LIQUIBASE` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `CONTEXTS` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `LABELS` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `DEPLOYMENT_ID` varchar(10) COLLATE utf8mb4_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `databasechangelog`
--

LOCK TABLES `databasechangelog` WRITE;
/*!40000 ALTER TABLE `databasechangelog` DISABLE KEYS */;
INSERT INTO `databasechangelog` VALUES ('00000000000001','jhipster','config/liquibase/changelog/00000000000000_initial_schema.xml','2022-04-28 09:46:38',1,'EXECUTED','8:ee6ac84cdbeb19acaf73c39298fb40f8','createTable tableName=jhi_user; createTable tableName=jhi_authority; createTable tableName=jhi_user_authority; addPrimaryKey tableName=jhi_user_authority; addForeignKeyConstraint baseTableName=jhi_user_authority, constraintName=fk_authority_name, ...','',NULL,'4.6.1',NULL,NULL,'1110392312'),('20220428081257-1','jhipster','config/liquibase/changelog/20220428081257_added_entity_Menu.xml','2022-04-28 16:19:20',2,'EXECUTED','8:78a95988dd89eb412aa72c226b0c6925','createTable tableName=sys_menu','',NULL,'4.6.1',NULL,NULL,'1133959792'),('20220428081257-1-data','jhipster','config/liquibase/changelog/20220428081257_added_entity_Menu.xml','2022-04-28 16:19:20',3,'EXECUTED','8:01a6ff240d4bb49283d3de14ba345521','loadData tableName=sys_menu','',NULL,'4.6.1','faker',NULL,'1133959792'),('20220503083341-1','jhipster','config/liquibase/changelog/20220503083341_added_entity_UiComponent.xml','2022-05-03 16:57:04',4,'EXECUTED','8:d1a8ed54189536ab5e60623a2f7133b4','createTable tableName=sys_uicomponent','',NULL,'4.6.1',NULL,NULL,'1568224373'),('20220503083341-1-data','jhipster','config/liquibase/changelog/20220503083341_added_entity_UiComponent.xml','2022-05-03 16:57:05',5,'EXECUTED','8:14e127fab9099393cf8b6922a14251a4','loadData tableName=sys_uicomponent','',NULL,'4.6.1','faker',NULL,'1568224373'),('20220503083342-1','jhipster','config/liquibase/changelog/20220503083342_added_entity_UiToolButton.xml','2022-05-03 16:57:05',6,'EXECUTED','8:a00984989faaa13d01c05329e5eeb066','createTable tableName=sys_uitoolbutton','',NULL,'4.6.1',NULL,NULL,'1568224373'),('20220503083342-1-data','jhipster','config/liquibase/changelog/20220503083342_added_entity_UiToolButton.xml','2022-05-03 16:57:05',7,'EXECUTED','8:cf6d3cd718598305ac4040c85d2fb4de','loadData tableName=sys_uitoolbutton','',NULL,'4.6.1','faker',NULL,'1568224373'),('20220503083343-1','jhipster','config/liquibase/changelog/20220503083343_added_entity_UiTable.xml','2022-05-03 16:57:06',8,'EXECUTED','8:6c227879eaf083133eff7e28aaa0a7f5','createTable tableName=sys_uitable','',NULL,'4.6.1',NULL,NULL,'1568224373'),('20220503083343-1-data','jhipster','config/liquibase/changelog/20220503083343_added_entity_UiTable.xml','2022-05-03 16:57:06',9,'EXECUTED','8:65b8683246ea12a32062164d3831d2fa','loadData tableName=sys_uitable','',NULL,'4.6.1','faker',NULL,'1568224373'),('20220503083344-1','jhipster','config/liquibase/changelog/20220503083344_added_entity_UiEditform.xml','2022-05-03 16:57:06',10,'EXECUTED','8:6e35bb04e028f4b22c5ceb841f3cb7ce','createTable tableName=sys_uieditform','',NULL,'4.6.1',NULL,NULL,'1568224373'),('20220503083344-1-data','jhipster','config/liquibase/changelog/20220503083344_added_entity_UiEditform.xml','2022-05-03 16:57:06',11,'EXECUTED','8:9ce4fb7f8f075080a0e133a4c45bf027','loadData tableName=sys_uieditform','',NULL,'4.6.1','faker',NULL,'1568224373'),('20220503083345-1','jhipster','config/liquibase/changelog/20220503083345_added_entity_UiQueryform.xml','2022-05-03 16:57:07',12,'EXECUTED','8:b185356129014a88b858dcacc58cbb97','createTable tableName=sys_uiqueryform','',NULL,'4.6.1',NULL,NULL,'1568224373'),('20220503083345-1-data','jhipster','config/liquibase/changelog/20220503083345_added_entity_UiQueryform.xml','2022-05-03 16:57:07',13,'EXECUTED','8:7d9d176176c1403be896486498104cda','loadData tableName=sys_uiqueryform','',NULL,'4.6.1','faker',NULL,'1568224373'),('20220504121625-1','jhipster','config/liquibase/changelog/20220504121625_added_entity_Example.xml','2022-05-04 20:34:55',14,'EXECUTED','8:b2cbe3b600976fb47508ded908be3693','createTable tableName=t_example','',NULL,'4.6.1',NULL,NULL,'1667694945'),('20220504121625-1-data','jhipster','config/liquibase/changelog/20220504121625_added_entity_Example.xml','2022-05-04 20:34:55',15,'EXECUTED','8:69f7e341c65f756008df04426334f341','loadData tableName=t_example','',NULL,'4.6.1','faker',NULL,'1667694945'),('20220504123346-1','jhipster','config/liquibase/changelog/20220504121625_added_entity_Example.xml','2022-05-05 16:59:46',16,'EXECUTED','8:b2cbe3b600976fb47508ded908be3693','createTable tableName=t_example','',NULL,'4.6.1',NULL,NULL,'1741186154'),('20220504123346-1-data','jhipster','config/liquibase/changelog/20220504121625_added_entity_Example.xml','2022-05-05 16:59:46',17,'EXECUTED','8:69f7e341c65f756008df04426334f341','loadData tableName=t_example','',NULL,'4.6.1','faker',NULL,'1741186154'),('20220507141955-1','jhipster','config/liquibase/changelog/20220507141955_added_entity_LeaveSlip.xml','2022-05-07 22:22:27',18,'EXECUTED','8:5933d84eecf76ca243e4a975ba7397ea','createTable tableName=t_leaveslip','',NULL,'4.6.1',NULL,NULL,'1933346695'),('20220507141955-1-data','jhipster','config/liquibase/changelog/20220507141955_added_entity_LeaveSlip.xml','2022-05-07 22:22:27',19,'EXECUTED','8:c2853df03bebde01bae62dc285eacc1a','loadData tableName=t_leaveslip','',NULL,'4.6.1','faker',NULL,'1933346695'),('20220508030550-1','jhipster','config/liquibase/changelog/20220508030550_added_entity_LeaveType.xml','2022-05-08 11:10:16',20,'EXECUTED','8:b9841d88feb49a742bea4ddfef2a9d3c','createTable tableName=ele_leavetype','',NULL,'4.6.1',NULL,NULL,'1979416033'),('20220508030550-1-data','jhipster','config/liquibase/changelog/20220508030550_added_entity_LeaveType.xml','2022-05-08 11:10:16',21,'EXECUTED','8:9c60885e7adcd1f1cb40c0b7985f2f9c','loadData tableName=ele_leavetype','',NULL,'4.6.1','faker',NULL,'1979416033'),('20220510065853-1','jhipster','config/liquibase/changelog/20220510065853_added_entity_UiTab.xml','2022-05-10 15:03:00',22,'EXECUTED','8:54625e5965044bf9c5e005b4ef9e017e','createTable tableName=sys_uitab','',NULL,'4.6.1',NULL,NULL,'2166179798'),('20220510065853-1-data','jhipster','config/liquibase/changelog/20220510065853_added_entity_UiTab.xml','2022-05-10 15:03:00',23,'EXECUTED','8:638d93222d02102a1b36418cdb948295','loadData tableName=sys_uitab','',NULL,'4.6.1','faker',NULL,'2166179798'),('20220514181352-1','jhipster','config/liquibase/changelog/20220514181352_added_entity_DataPermission.xml','2022-05-15 02:16:14',24,'EXECUTED','8:47c14f534ecd78551f13f641f004da6a','createTable tableName=sys_data_permissions','',NULL,'4.6.1',NULL,NULL,'2552173977'),('20220514181352-1-data','jhipster','config/liquibase/changelog/20220514181352_added_entity_DataPermission.xml','2022-05-15 02:16:14',25,'EXECUTED','8:73f5903c34625de2687a42c74d8c35dd','loadData tableName=sys_data_permissions','',NULL,'4.6.1','faker',NULL,'2552173977'),('20220515070013-1','jhipster','config/liquibase/changelog/20220515070013_added_entity_DataPermissionDetails.xml','2022-05-15 15:01:57',26,'EXECUTED','8:39de15badf2acd6b0dafd4b97e842ee6','createTable tableName=sys_data_permission_details','',NULL,'4.6.1',NULL,NULL,'2598116848'),('20220515070013-1-data','jhipster','config/liquibase/changelog/20220515070013_added_entity_DataPermissionDetails.xml','2022-05-15 15:01:57',27,'EXECUTED','8:bb5d6ff1f0f2a34e428bdb56e3414555','loadData tableName=sys_data_permission_details','',NULL,'4.6.1','faker',NULL,'2598116848'),('20220515082952-1','jhipster','config/liquibase/changelog/20220515082952_added_entity_DataPermissionsRel.xml','2022-05-15 16:34:37',28,'EXECUTED','8:0967b6172cf96b7074fa4e36d40eac12','createTable tableName=sys_data_permissions_rel','',NULL,'4.6.1',NULL,NULL,'2603677272'),('20220515082952-1-data','jhipster','config/liquibase/changelog/20220515082952_added_entity_DataPermissionsRel.xml','2022-05-15 16:34:38',29,'EXECUTED','8:13a9128d07f5cf2fb70f01c2a4decde8','loadData tableName=sys_data_permissions_rel','',NULL,'4.6.1','faker',NULL,'2603677272'),('20220515130727-1','jhipster','config/liquibase/changelog/20220515130727_added_entity_RoleMenu.xml','2022-05-15 21:10:01',30,'EXECUTED','8:384441a05500e8c6201ac9794b03ce0d','createTable tableName=sys_role_menu','',NULL,'4.6.1',NULL,NULL,'2620201300'),('20220515130727-1-data','jhipster','config/liquibase/changelog/20220515130727_added_entity_RoleMenu.xml','2022-05-15 21:10:02',31,'EXECUTED','8:dbf7241664a36220ed4d35e0b8c82e68','loadData tableName=sys_role_menu','',NULL,'4.6.1','faker',NULL,'2620201300'),('20220515132234-1','jhipster','config/liquibase/changelog/20220515132234_added_entity_RoleMenuToolButton.xml','2022-05-15 21:24:25',32,'EXECUTED','8:3c91410f0c2c3a1897c29583c5acf242','createTable tableName=sys_role_menu_button','',NULL,'4.6.1',NULL,NULL,'2621064678'),('20220515132234-1-data','jhipster','config/liquibase/changelog/20220515132234_added_entity_RoleMenuToolButton.xml','2022-05-15 21:24:25',33,'EXECUTED','8:1de8db3ef0fd257cd180252f7f6aac89','loadData tableName=sys_role_menu_button','',NULL,'4.6.1','faker',NULL,'2621064678'),('20220602024934-1','jhipster','config/liquibase/changelog/20220602024934_added_entity_RequestLogging.xml','2022-06-02 10:53:42',34,'EXECUTED','8:6300a927be1d5f2fa15a858c4f54751a','createTable tableName=sys_request_log','',NULL,'4.6.1',NULL,NULL,'4138421781'),('20220602024934-1-data','jhipster','config/liquibase/changelog/20220602024934_added_entity_RequestLogging.xml','2022-06-02 10:53:42',35,'EXECUTED','8:c7d1f1d669d14967ca85089f2ca224d3','loadData tableName=sys_request_log','',NULL,'4.6.1','faker',NULL,'4138421781'),('20220602024935-1','jhipster','config/liquibase/changelog/20220602024935_added_entity_SlowSqlLogging.xml','2022-06-02 10:53:43',36,'EXECUTED','8:2c90d2710b5ab115fb9a4eb00f1ccf32','createTable tableName=sys_slow_sql_log','',NULL,'4.6.1',NULL,NULL,'4138421781'),('20220602024935-1-data','jhipster','config/liquibase/changelog/20220602024935_added_entity_SlowSqlLogging.xml','2022-06-02 10:53:43',37,'EXECUTED','8:375656c6dfba4743ffd5340efb1f4cb3','loadData tableName=sys_slow_sql_log','',NULL,'4.6.1','faker',NULL,'4138421781'),('20220602071343-1','jhipster','config/liquibase/changelog/20220602071343_added_entity_TaskParam.xml','2022-06-02 15:17:03',38,'EXECUTED','8:fc94facd87b8bd017e9b17719171cad5','createTable tableName=sys_task_param','',NULL,'4.6.1',NULL,NULL,'4154222976'),('20220602071343-1-data','jhipster','config/liquibase/changelog/20220602071343_added_entity_TaskParam.xml','2022-06-02 15:17:03',39,'EXECUTED','8:750cb623e2071f3e1d7636bc80cd7355','loadData tableName=sys_task_param','',NULL,'4.6.1','faker',NULL,'4154222976'),('20220602071344-1','jhipster','config/liquibase/changelog/20220602071344_added_entity_SystemParam.xml','2022-06-02 15:17:04',40,'EXECUTED','8:b8602efa597ad3eb514bda9cf05d4c10','createTable tableName=sys_param','',NULL,'4.6.1',NULL,NULL,'4154222976'),('20220602071344-1-data','jhipster','config/liquibase/changelog/20220602071344_added_entity_SystemParam.xml','2022-06-02 15:17:04',41,'EXECUTED','8:a4af8f3c67131288a85878a15b452258','loadData tableName=sys_param','',NULL,'4.6.1','faker',NULL,'4154222976');
/*!40000 ALTER TABLE `databasechangelog` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `databasechangeloglock`
--

DROP TABLE IF EXISTS `databasechangeloglock`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `databasechangeloglock` (
  `ID` int(11) NOT NULL,
  `LOCKED` bit(1) NOT NULL,
  `LOCKGRANTED` datetime DEFAULT NULL,
  `LOCKEDBY` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `databasechangeloglock`
--

LOCK TABLES `databasechangeloglock` WRITE;
/*!40000 ALTER TABLE `databasechangeloglock` DISABLE KEYS */;
INSERT INTO `databasechangeloglock` VALUES (1,'\0',NULL,NULL);
/*!40000 ALTER TABLE `databasechangeloglock` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ele_leavetype`
--

DROP TABLE IF EXISTS `ele_leavetype`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ele_leavetype` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `code` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `parentid` bigint(20) DEFAULT NULL COMMENT 'select ÊòØ‰∏çÈúÄË¶ÅparentidÁöÑÔºå‰∏∫Ë¶ÅÁ¥†Ë°®ele_Áªü‰∏ÄÂ¢ûÂä†ËØ•Â≠óÊÆµ',
  `enabled` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='ËØ∑ÂÅáÁ±ªÂûã\\n\\n@author zhaozhiwei';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ele_leavetype`
--

LOCK TABLES `ele_leavetype` WRITE;
/*!40000 ALTER TABLE `ele_leavetype` DISABLE KEYS */;
INSERT INTO `ele_leavetype` VALUES (11,'sick-leave','ÁóÖÂÅá',0,''),(12,'casual-leave','‰∫ãÂÅá',0,''),(13,'marriage-leave','Â©öÂÅá',0,'');
/*!40000 ALTER TABLE `ele_leavetype` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `jhi_authority`
--

DROP TABLE IF EXISTS `jhi_authority`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `jhi_authority` (
  `name` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `jhi_authority`
--

LOCK TABLES `jhi_authority` WRITE;
/*!40000 ALTER TABLE `jhi_authority` DISABLE KEYS */;
INSERT INTO `jhi_authority` VALUES ('ROLE_ADMIN'),('ROLE_USER');
/*!40000 ALTER TABLE `jhi_authority` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `jhi_user`
--

DROP TABLE IF EXISTS `jhi_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `jhi_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `login` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `password_hash` varchar(60) COLLATE utf8mb4_unicode_ci NOT NULL,
  `first_name` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `last_name` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `email` varchar(191) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `image_url` varchar(256) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `activated` bit(1) NOT NULL,
  `lang_key` varchar(10) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `activation_key` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `reset_key` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `created_by` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `created_date` timestamp NULL,
  `reset_date` timestamp NULL DEFAULT NULL,
  `last_modified_by` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `last_modified_date` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `ux_user_login` (`login`),
  UNIQUE KEY `ux_user_email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `jhi_user`
--

LOCK TABLES `jhi_user` WRITE;
/*!40000 ALTER TABLE `jhi_user` DISABLE KEYS */;
INSERT INTO `jhi_user` VALUES (1,'admin','$2a$10$gSAhZrxMllrbgj/kkK9UceBPpChGWJA7SYIb1Mqo.n5aNLq1/oRrC','Administrator','Administrator','admin@localhost','','','zh-cn',NULL,NULL,'system',NULL,NULL,'system',NULL),(2,'user','$2a$10$VEjxo0jq2YG9Rbk2HmX9S.k1uZBGYUHdUcid3g/vfiEl7lwWgOH/K','User','User','user@localhost','','','zh-cn',NULL,NULL,'system',NULL,NULL,'system',NULL);
/*!40000 ALTER TABLE `jhi_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `jhi_user_authority`
--

DROP TABLE IF EXISTS `jhi_user_authority`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `jhi_user_authority` (
  `user_id` bigint(20) NOT NULL,
  `authority_name` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`user_id`,`authority_name`),
  KEY `fk_authority_name` (`authority_name`),
  CONSTRAINT `fk_authority_name` FOREIGN KEY (`authority_name`) REFERENCES `jhi_authority` (`name`),
  CONSTRAINT `fk_user_id` FOREIGN KEY (`user_id`) REFERENCES `jhi_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `jhi_user_authority`
--

LOCK TABLES `jhi_user_authority` WRITE;
/*!40000 ALTER TABLE `jhi_user_authority` DISABLE KEYS */;
INSERT INTO `jhi_user_authority` VALUES (1,'ROLE_ADMIN'),(1,'ROLE_USER'),(2,'ROLE_USER');
/*!40000 ALTER TABLE `jhi_user_authority` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_data_permission_details`
--

DROP TABLE IF EXISTS `sys_data_permission_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_data_permission_details` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `rule_id` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT 'ÊùÉÈôêÂêçÁß∞',
  `left_bracket` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT 'Â∑¶Êã¨Âè∑',
  `jhi_column` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `op` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `value` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `right_bracket` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT 'Âè≥Êã¨Âè∑',
  `ordernum` int(11) DEFAULT NULL,
  `logical_rel` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='Êï∞ÊçÆÊùÉÈôêÊòéÁªÜ\\n@author zhaozhiwei';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_data_permission_details`
--

LOCK TABLES `sys_data_permission_details` WRITE;
/*!40000 ALTER TABLE `sys_data_permission_details` DISABLE KEYS */;
/*!40000 ALTER TABLE `sys_data_permission_details` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_data_permissions`
--

DROP TABLE IF EXISTS `sys_data_permissions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_data_permissions` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `code` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT 'ÊùÉÈôêÁºñÁ†Å',
  `name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT 'ÊùÉÈôêÂêçÁß∞',
  `rule_sql` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT 'ËßÑÂàôsql, ÈÖçÁΩÆÊòéÁªÜÊó∂ÂÄôÊâçÁîüÊàê',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='Êï∞ÊçÆÊùÉÈôê‰∏ªË°®\\n@author zhaozhiwei';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_data_permissions`
--

LOCK TABLES `sys_data_permissions` WRITE;
/*!40000 ALTER TABLE `sys_data_permissions` DISABLE KEYS */;
INSERT INTO `sys_data_permissions` VALUES (1,'all_data_permission','ÂÖ®ÈÉ®Êï∞ÊçÆÊùÉÈôê',NULL),(2,'institution_data_permission','Êú∫ÊûÑÊï∞ÊçÆÊùÉÈôê',NULL),(3,'institution_with_following_data_permission','Êú∫ÊûÑÂèä‰ª•‰∏ãÊï∞ÊçÆÊùÉÈôê',NULL),(4,'personal_only_data_permission','‰ªÖÊú¨‰∫∫Êï∞ÊçÆÊùÉÈôê',NULL),(5,'','ÊµãËØïÂä®ÊÄÅÊùÉÈôê','name=\'Âº†‰∏â\'');
/*!40000 ALTER TABLE `sys_data_permissions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_data_permissions_rel`
--

DROP TABLE IF EXISTS `sys_data_permissions_rel`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_data_permissions_rel` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `rule_id` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT 'ÊùÉÈôêid',
  `role_id` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT 'ËßíËâ≤id',
  `menu_id` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT 'ËèúÂçïid',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='ËßíËâ≤, ËèúÂçï, Êï∞ÊçÆÊùÉÈôêÂÖ≥Á≥ª\\nËßíËâ≤ËÉΩÁúãÂæóÊâÄÊúâËèúÂçï‰∏ç‰∏ÄÂÆöÈÉΩË¶ÅÊúâÊï∞ÊçÆÊùÉÈôê, ‰ΩÜÊòØÂè™ÊúâËÉΩÁúãÂà∞ËèúÂçïÊâçËÉΩÂéªÈÖçÁΩÆÊùÉÈôê\\n@author zhaozhiwei';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_data_permissions_rel`
--

LOCK TABLES `sys_data_permissions_rel` WRITE;
/*!40000 ALTER TABLE `sys_data_permissions_rel` DISABLE KEYS */;
INSERT INTO `sys_data_permissions_rel` VALUES (6,'1','ROLE_ADMIN','6'),(7,'5','ROLE_ADMIN','4');
/*!40000 ALTER TABLE `sys_data_permissions_rel` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_menu`
--

DROP TABLE IF EXISTS `sys_menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_menu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `component` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `icon_cls` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `keep_alive` bit(1) DEFAULT NULL,
  `require_auth` bit(1) DEFAULT NULL,
  `parent_id` bigint(20) DEFAULT NULL,
  `enabled` bit(1) DEFAULT NULL,
  `config` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `ordernum` int(11) DEFAULT NULL,
  `url` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_menu`
--

LOCK TABLES `sys_menu` WRITE;
/*!40000 ALTER TABLE `sys_menu` DISABLE KEYS */;
INSERT INTO `sys_menu` VALUES (1,NULL,'È¶ñÈ°µ','el-icon-s-home','','\0',0,'','{}',1,'/'),(2,NULL,'Á§∫‰æãËèúÂçï','el-icon-share','\0','',0,'','{}',2,'#'),(3,NULL,'ÊºîÁ§∫-HelloWorld','el-icon-share','\0','\0',2,'','{component:\"example/hello-world\"}',3,'/example/helloworld'),(4,NULL,'ÊºîÁ§∫-Âä®ÊÄÅÁïåÈù¢','el-icon-star-on','\0','\0',2,'','{component:\"example/uiexample\"}',4,'/example/uiexample'),(5,NULL,'ÊºîÁ§∫-ËØ∑ÂÅáÂçïÂΩïÂÖ•','el-icon-share','\0','\0',2,'','{component:\"example/leaveslip-add\"}',5,'/example/leaveslip/add'),(6,NULL,'ÊºîÁ§∫-ËØ∑ÂÅáÂçïÂÆ°Ê†∏','el-icon-share','\0','\0',2,'','{component:\"example/leaveslip-audit\"}',6,'/example/leaveslip/audit'),(7,NULL,'ËèúÂçïÁÆ°ÁêÜ','el-icon-s-data','\0','\0',0,'','{}',7,'#1'),(8,NULL,'Âü∫Á°ÄË¶ÅÁ¥†Áª¥Êä§','el-icon-s-data','\0','\0',0,'','{}',8,'#'),(9,NULL,'ËèúÂçïÁª¥Êä§',NULL,'\0','\0',7,'','{}',9,'/menu/'),(10,NULL,'ÁªÑ‰ª∂ÈÖçÁΩÆ',NULL,'\0','\0',7,'','{}',10,'/ui-component/'),(11,NULL,'ÊåâÈíÆÈÖçÁΩÆ',NULL,'\0','\0',7,'','{}',11,'/ui-tool-button/'),(12,NULL,'ÂàóË°®ÈÖçÁΩÆ',NULL,'\0','\0',7,'','{}',12,'/ui-table/'),(13,NULL,'ÁºñËæëÂå∫ÈÖçÁΩÆ',NULL,'\0','\0',7,'','{}',13,'/ui-editform/'),(14,NULL,'Êü•ËØ¢Âå∫ÈÖçÁΩÆ',NULL,'\0','\0',7,'','{}',14,'/ui-queryform/'),(15,NULL,'È°µÁ≠æÂå∫ÈÖçÁΩÆ',NULL,'\0','\0',7,'','{}',15,'/ui-tab/'),(16,NULL,'ËØ∑ÂÅáÁ±ªÂûãÁª¥Êä§',NULL,'\0','\0',8,'','{}',16,'/leave-type/'),(17,NULL,'ÊùÉÈôêÁÆ°ÁêÜ','el-icon-user-solid','\0','\0',0,'','{}',17,'#'),(18,NULL,'Êï∞ÊçÆÊùÉÈôêÁª¥Êä§',NULL,'\0','\0',17,'','{}',18,'/data-permission/'),(19,NULL,'Êï∞ÊçÆÊùÉÈôêÂàÜÈÖç',NULL,'\0','\0',17,'','{component:\"permission/data-permission\"}',19,'/sys/data-permission/create'),(20,NULL,'ÂäüËÉΩÊùÉÈôêÂàÜÈÖç',NULL,'\0','\0',17,'','{component:\"permission/tool-button-permission\"}',20,'/sys/tool-button-permission/create'),(21,NULL,'Â§ßÂ±èÁÆ°ÁêÜ','el-icon-s-platform','\0','\0',0,'','{}',21,'#'),(22,NULL,'Á≥ªÁªüÈÖçÁΩÆ','el-icon-setting','\0','\0',0,'','{}',22,'#'),(23,NULL,'Á≥ªÁªüÂèÇÊï∞ÈÖçÁΩÆ',NULL,'\0','\0',22,'','{}',23,'/system-param/'),(24,NULL,'ÂÆöÊó∂‰ªªÂä°ÈÖçÁΩÆ',NULL,'\0','\0',22,'','{}',24,'/task-param/'),(25,NULL,'‰ª™Ë°®ÁõòÁªÑ‰ª∂Áª¥Êä§',NULL,'\0','\0',21,'','{}',25,'#'),(26,NULL,'‰ª™Ë°®ÁõòÁªÑ‰ª∂ÈÖçÁΩÆ',NULL,'\0','\0',21,'','{}',26,'#'),(27,NULL,'Á≥ªÁªüÁõëÊéß','el-icon-camera-solid','\0','\0',0,'','{}',27,'#'),(28,NULL,'Âú®Á∫ø‰∫∫ÂëòÁõëÊéß',NULL,'\0','\0',27,'','{component:\"manager/online-user\"}',28,'/sys/manage/online/users'),(29,NULL,'Êìç‰ΩúÊó•ÂøóÁõëÊéß',NULL,'\0','\0',27,'','{}',29,'/request-logging/'),(30,NULL,'ÈááÈõÜË°®ÂÆö‰πâ',NULL,'\0','\0',22,'','{}',30,'#'),(31,NULL,'ËèúÂçïËßíËâ≤ÊùÉÈôêÂàÜÈÖç',NULL,'\0','\0',17,'','{component:\"permission/role-menu\"}',31,'/sys/role-menu/create'),(32,NULL,'ÊÖ¢sqlÁõëÊéß',NULL,'\0','\0',27,'','{}',32,'/slow-sql-logging/');
/*!40000 ALTER TABLE `sys_menu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_param`
--

DROP TABLE IF EXISTS `sys_param`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_param` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `code` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT 'ÈÖçÁΩÆÁºñÁ†Å\\n\\n‰ΩøÁî®Êó∂ÈÄöËøáÁºñÁ†ÅËé∑ÂèñÂèÇÊï∞',
  `name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT 'ÈÖçÁΩÆÂêçÁß∞',
  `value` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT 'ÂèÇÊï∞‰ø°ÊÅØ\\nÂèØ‰ª•ÊòØÊôÆÈÄövalueÊàñËÄÖjsonÁ≠âÔºå‰ΩøÊó∂Ëá™Ë°åËß£Êûê',
  `remark` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT 'Â§áÊ≥®\\nÊ†πÊçÆÈúÄË¶ÅÂØπÂèÇÊï∞Êõ¥ÁªÜËá¥ÁöÑÊèèËø∞',
  `enable` bit(1) DEFAULT NULL COMMENT 'ÊòØÂê¶ÂêØÁî®',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='Á≥ªÁªüÂèÇÊï∞‰ø°ÊÅØ\\n\\n‰∏Ä‰∫õÈúÄË¶ÅÁªèÂ∏∏ÊÄßÊâãÂä®Ë∞ÉÊï¥, Ë∑ü‰∏öÂä°Áõ∏ÂÖ≥ÁöÑÈÖçÁΩÆÊîæËøôÈáå\\nÁ®ãÂ∫èÁõ∏ÂÖ≥ÁöÑÂèØ‰ª•ÊñπÂºèspringÈÖçÁΩÆÊñá‰ª∂\\n\\n@author zhaozhiwei';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_param`
--

LOCK TABLES `sys_param` WRITE;
/*!40000 ALTER TABLE `sys_param` DISABLE KEYS */;
/*!40000 ALTER TABLE `sys_param` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_request_log`
--

DROP TABLE IF EXISTS `sys_request_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_request_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `trace_id` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT 'ËØ∑Ê±ÇÂîØ‰∏Äid, Êñπ‰æøÈóÆÈ¢òÂÆö‰Ωç',
  `login_name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT 'Áî®Êà∑Âêç',
  `request_uri` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT 'ËØ∑Ê±ÇÂú∞ÂùÄ',
  `client_ip` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT 'ÂÆ¢Êà∑Á´Øip',
  `jhi_current_time` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT 'ÂΩìÂâçÊó∂Èó¥',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=825 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='Áî®Êà∑ËØ∑Ê±ÇÊó•Âøó‰ø°ÊÅØ\\n@author zhaozhiwei';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_request_log`
--

LOCK TABLES `sys_request_log` WRITE;
/*!40000 ALTER TABLE `sys_request_log` DISABLE KEYS */;
INSERT INTO `sys_request_log` VALUES (2,'Manager cross-platform','feed','Gibraltar 1080p ÂÜÖËíôÂè§','Frozen Stand-alone Architect','synthesize innovate'),(3,'Orchestrator','Garden up bleeding-edge','monitor Soft','Agent','deposit'),(4,'pink Account','innovate blue','ÊµôÊ±üÁúÅ','Kids','sensor Bacon Berkshire'),(5,'Liaison functionalities withdrawal','white didactic Table','product orchid','enhance Refined','extensible markets'),(6,'Borders quantifying SDD','input','Ball needs-based Incredible','Multi-tiered Peru Tools','mission-critical Officer'),(7,'Future cross-platform CFA','Investment payment XML','Berkshire ÁîòËÇÉÁúÅ','connect Quetzal Â±±Ë•øÁúÅ','vortals'),(8,'Factors plum','HTTP Multi-tiered','Cheese','XSS bleeding-edge Checking','Southern enterprise'),(9,'Metrics','Peso','high-level JSON invoice','web-enabled cross-platform homogeneous','platforms moderator architect'),(11,'b20fdc59-80f1-4cb5-a5c4-5dba8bebf898','anonymousUser','/api/authenticate','0:0:0:0:0:0:0:1','2022-06-02 11:17:31'),(12,'8851dae8-ec15-4305-a37a-d64504e1b6ed','admin','/api/account','0:0:0:0:0:0:0:1','2022-06-02 11:17:33'),(13,'7327063a-4cca-44cb-8df0-ed3de350e1ee','admin','/api/account','0:0:0:0:0:0:0:1','2022-06-02 11:17:36'),(14,'8b82d9f3-603f-4700-afc6-3ecd200fb302','admin','/api/account','0:0:0:0:0:0:0:1','2022-06-02 11:17:36'),(15,'cf0ac0d1-da40-45a6-8d02-098de1fea604','admin','/api/account','0:0:0:0:0:0:0:1','2022-06-02 11:17:36'),(16,'21094640-47fb-4fa8-bd93-80e9d17e7347','admin','/api/menus/tree','0:0:0:0:0:0:0:1','2022-06-02 11:17:36'),(17,'e48ce034-e925-4794-bbdd-8412d87c59bf','admin','/api/request-loggings','0:0:0:0:0:0:0:1','2022-06-02 11:17:57'),(18,'798c9c32-9164-45de-bf8e-b8cb2f40c75a','admin','/api/slow-sql-loggings','0:0:0:0:0:0:0:1','2022-06-02 11:18:31'),(19,'98b868cc-cd62-48b7-980e-fed3abbd7e94','admin','/api/examples/page/1/size/5','0:0:0:0:0:0:0:1','2022-06-02 11:18:37'),(20,'d9986c42-4e11-455e-a1d8-29e932e1b215','admin','/api/ui-components/menu/0000','0:0:0:0:0:0:0:1','2022-06-02 11:18:37'),(21,'799d3f0a-d379-49ea-9207-4d01800bd812','admin','/api/ui-editforms/menu/0000','0:0:0:0:0:0:0:1','2022-06-02 11:18:38'),(22,'f2811c04-325f-47ba-9173-1dbd48bcd007','admin','/api/ui-tool-buttons/menu/0000','0:0:0:0:0:0:0:1','2022-06-02 11:18:38'),(23,'84f3c899-04cc-42aa-b0d2-ea7308506fba','admin','/api/ui-tables/menu/0000','0:0:0:0:0:0:0:1','2022-06-02 11:18:38'),(24,'cb5a0c88-d973-49fb-b394-a197918ac618','admin','/api/ui-tabs/menu/0000','0:0:0:0:0:0:0:1','2022-06-02 11:18:38'),(25,'1bac4150-563b-41b5-8bd9-2b62365c5f24','admin','/api/ui-components/menu/5','0:0:0:0:0:0:0:1','2022-06-02 11:18:38'),(26,'d8ae9d35-bdb0-4ae3-95d6-bd4db6d4d3ba','admin','/api/ui-editforms/menu/5','0:0:0:0:0:0:0:1','2022-06-02 11:18:39'),(27,'62343dcd-8f3a-4947-a270-c118f243f11b','admin','/api/ui-tool-buttons/menu/5','0:0:0:0:0:0:0:1','2022-06-02 11:18:39'),(28,'492bb37e-4a76-4fad-9876-77b9c79c85a2','admin','/api/ui-components/menu/6','0:0:0:0:0:0:0:1','2022-06-02 11:18:39'),(29,'9483c779-d481-45c2-b428-617b0e0e23fe','admin','/api/leave-slips','0:0:0:0:0:0:0:1','2022-06-02 11:18:39'),(30,'d5b44010-3d69-4b14-a677-f5895e7fe068','admin','/api/ui-tool-buttons/menu/6','0:0:0:0:0:0:0:1','2022-06-02 11:18:39'),(31,'d6467b9b-efb2-47b4-9826-6700e7183541','admin','/api/ui-tables/menu/6','0:0:0:0:0:0:0:1','2022-06-02 11:18:39'),(32,'612014f9-4b55-43a4-ac5f-f29cee8b9bc3','admin','/api/ui-tool-buttons','0:0:0:0:0:0:0:1','2022-06-02 11:18:42'),(33,'e864ca0d-174a-416b-b5e1-134d8f6ff60b','admin','/api/ui-tables','0:0:0:0:0:0:0:1','2022-06-02 11:18:42'),(34,'05f972d7-7fd1-42a0-a68f-6e6cb565db3d','admin','/api/ui-tabs','0:0:0:0:0:0:0:1','2022-06-02 11:18:43'),(35,'f5407413-3ef2-47fb-a801-2141a1e0ba21','admin','/api/leave-types','0:0:0:0:0:0:0:1','2022-06-02 11:18:45'),(36,'1cdec27b-ee7e-4124-9b07-d644e3f507a9','admin','/api/roles/tree','0:0:0:0:0:0:0:1','2022-06-02 11:18:47'),(37,'a88e95cb-6118-4da8-99c2-a472b83881a9','admin','/api/menus/tree','0:0:0:0:0:0:0:1','2022-06-02 11:18:47'),(38,'900bd290-39b2-49aa-b2ca-60242e545329','admin','/api/roles/tree','0:0:0:0:0:0:0:1','2022-06-02 11:18:48'),(39,'961e8626-e6ef-4f47-8b5f-8a6386e4155e','admin','/api/data-permissions/tree','0:0:0:0:0:0:0:1','2022-06-02 11:18:48'),(40,'ac5bad28-bb48-45db-a4af-26a524b0aac0','admin','/api/menus/tree','0:0:0:0:0:0:0:1','2022-06-02 11:18:48'),(41,'c8214821-2c0c-4f72-8223-76b68cfb3d20','admin','/api/slow-sql-loggings','0:0:0:0:0:0:0:1','2022-06-02 11:18:51'),(42,'df3dd44e-180f-421a-9552-77e2e4ba1e3c','admin','/api/slow-sql-loggings/2','0:0:0:0:0:0:0:1','2022-06-02 11:18:56'),(43,'d48aa4bf-1037-4c4f-aaa9-7496f32ff956','admin','/api/slow-sql-loggings','0:0:0:0:0:0:0:1','2022-06-02 11:18:56'),(44,'d5241315-68cc-4d08-b0d6-23f233e3b9fa','admin','/api/slow-sql-loggings/3','0:0:0:0:0:0:0:1','2022-06-02 11:18:58'),(45,'7dd809b1-11b8-4d59-88dd-a01213c72803','admin','/api/slow-sql-loggings','0:0:0:0:0:0:0:1','2022-06-02 11:18:59'),(46,'08fea5c3-778f-4cc6-8348-5c741915b1e0','admin','/api/slow-sql-loggings/4','0:0:0:0:0:0:0:1','2022-06-02 11:19:01'),(47,'ef5d5bf7-2f32-416c-b96b-6cc17158b249','admin','/api/slow-sql-loggings','0:0:0:0:0:0:0:1','2022-06-02 11:19:01'),(48,'3901c1f3-384b-4702-a3dc-1445e01db964','admin','/api/slow-sql-loggings/5','0:0:0:0:0:0:0:1','2022-06-02 11:19:03'),(49,'9617a8ea-455c-4945-8f0c-9b2011792ba8','admin','/api/slow-sql-loggings','0:0:0:0:0:0:0:1','2022-06-02 11:19:03'),(50,'a73e15c5-8a04-4c7b-b6f8-6705e1be75a7','admin','/api/slow-sql-loggings/6','0:0:0:0:0:0:0:1','2022-06-02 11:19:05'),(51,'c156004c-27ef-47ce-a18c-c06ca94cf9d5','admin','/api/slow-sql-loggings','0:0:0:0:0:0:0:1','2022-06-02 11:19:05'),(52,'300b80b9-1d8d-41ff-a3a1-f53196720ebc','admin','/api/slow-sql-loggings/7','0:0:0:0:0:0:0:1','2022-06-02 11:19:07'),(53,'12302513-ae34-4c4d-aeb9-9adc70695805','admin','/api/slow-sql-loggings','0:0:0:0:0:0:0:1','2022-06-02 11:19:08'),(54,'1b42c8e9-a3df-435f-b61c-626fd9e34741','admin','/api/slow-sql-loggings/8','0:0:0:0:0:0:0:1','2022-06-02 11:19:10'),(55,'ac6871ec-b02a-4dd6-aedb-5b26ae37a71d','admin','/api/slow-sql-loggings','0:0:0:0:0:0:0:1','2022-06-02 11:19:10'),(56,'89cfff71-80be-44e6-87a4-371508638e02','admin','/api/slow-sql-loggings/9','0:0:0:0:0:0:0:1','2022-06-02 11:19:12'),(57,'9d0b886a-f542-4fb2-85c4-6fd2c8825fe8','admin','/api/slow-sql-loggings','0:0:0:0:0:0:0:1','2022-06-02 11:19:12'),(58,'8d8f3b23-6e51-4403-b9eb-97775da9099c','admin','/api/request-loggings','0:0:0:0:0:0:0:1','2022-06-02 11:19:20'),(59,'6490089c-c7a2-4d04-8de5-8eff3e5b8172','admin','/api/admin/users','0:0:0:0:0:0:0:1','2022-06-02 11:19:54'),(60,'6f8c0bd0-f3b7-41a5-b3b8-46fcbc91b2bc','admin','/api/account','0:0:0:0:0:0:0:1','2022-06-02 11:22:59'),(61,'ecbe897f-4567-4dae-80e1-9f1b6ec52767','admin','/api/menus/tree','0:0:0:0:0:0:0:1','2022-06-02 11:22:59'),(62,'ecb1ca1f-874d-4c1d-a70c-ec8763b6cc7d','admin','/api/account','0:0:0:0:0:0:0:1','2022-06-02 11:22:59'),(63,'f09d0742-39e9-447d-8d6b-8c08f6e06192','admin','/api/ui-components/menu/0000','0:0:0:0:0:0:0:1','2022-06-02 11:23:04'),(64,'8640e99b-f566-42fe-87e2-9e583587a28f','admin','/api/examples/page/1/size/5','0:0:0:0:0:0:0:1','2022-06-02 11:23:04'),(65,'d897fc13-9280-4d55-9ab2-ddcaefd9cec9','admin','/api/ui-editforms/menu/0000','0:0:0:0:0:0:0:1','2022-06-02 11:23:04'),(66,'16db650a-8f0a-4d52-a18b-cfc71e299d71','admin','/api/ui-tool-buttons/menu/0000','0:0:0:0:0:0:0:1','2022-06-02 11:23:04'),(67,'67d420d7-19b7-4189-a448-217e586b1d1b','admin','/api/ui-tables/menu/0000','0:0:0:0:0:0:0:1','2022-06-02 11:23:04'),(68,'26984251-b3e4-45e7-8b26-6109266e2218','admin','/api/ui-tabs/menu/0000','0:0:0:0:0:0:0:1','2022-06-02 11:23:04'),(69,'dab3fbd0-9151-4328-9268-3e5cbd4453ea','admin','/api/ui-components/menu/5','0:0:0:0:0:0:0:1','2022-06-02 11:23:05'),(70,'602fe9fa-aa8d-4173-88e1-d7af1d9cfa9c','admin','/api/ui-editforms/menu/5','0:0:0:0:0:0:0:1','2022-06-02 11:23:06'),(71,'f2772ffe-7db6-4326-9efa-cd179e89d5f1','admin','/api/ui-tool-buttons/menu/5','0:0:0:0:0:0:0:1','2022-06-02 11:23:06'),(72,'0e693bfc-f71e-43fd-9c50-0ad3cce288f5','anonymousUser','/error','127.0.0.1','2022-06-02 11:29:36'),(73,'80073f7d-4231-478e-8781-fbf3cf1d49d7','anonymousUser','/error','127.0.0.1','2022-06-02 11:29:36'),(74,'573dea70-846c-48bf-989b-5e9267f68f90','anonymousUser','/error','127.0.0.1','2022-06-02 11:29:38'),(75,'ec4b243d-d625-4fb4-a513-16c7d93ed701','anonymousUser','/error','127.0.0.1','2022-06-02 11:29:40'),(76,'838b8e45-8851-4a30-b12c-e9dcebc9b0eb','anonymousUser','/error','127.0.0.1','2022-06-02 11:29:42'),(77,'0988d6a2-9f4e-48f8-a874-656dfccbaab6','admin','/api/menus/tree','0:0:0:0:0:0:0:1','2022-06-02 11:29:50'),(78,'d52ad72d-c3fb-4171-808b-dee327b49f49','admin','/api/account','0:0:0:0:0:0:0:1','2022-06-02 11:29:50'),(79,'01b10f1b-1348-4856-9735-f5f0d9cd9c90','admin','/api/account','0:0:0:0:0:0:0:1','2022-06-02 11:29:50'),(80,'4aa1a7ec-cfbf-4849-953f-d6c1ad764b92','admin','/api/account','0:0:0:0:0:0:0:1','2022-06-02 11:29:50'),(81,'96259a50-3e46-4f24-a95c-0f45ff1889a6','admin','/v3/api-docs/springdocDefault','0:0:0:0:0:0:0:1','2022-06-02 11:29:51'),(82,'9b5fb27b-53ed-4970-ae93-620fa433915a','admin','/api/ui-components/menu/0000','0:0:0:0:0:0:0:1','2022-06-02 11:30:03'),(83,'060c789c-9efa-40fe-9424-fe746b7d2699','admin','/api/examples/page/1/size/5','0:0:0:0:0:0:0:1','2022-06-02 11:30:03'),(84,'dff93f3b-5dd2-4367-b2a0-b860c5f3644c','admin','/api/ui-editforms/menu/0000','0:0:0:0:0:0:0:1','2022-06-02 11:30:03'),(85,'09acebaf-4119-4d51-b49e-863dd4af1794','admin','/api/ui-tool-buttons/menu/0000','0:0:0:0:0:0:0:1','2022-06-02 11:30:03'),(86,'144da716-4bc1-41c6-ba2c-ffdf69c969b1','admin','/api/ui-tables/menu/0000','0:0:0:0:0:0:0:1','2022-06-02 11:30:03'),(87,'147bed73-80c9-4cc0-8b18-0677790bb8e4','admin','/api/ui-tabs/menu/0000','0:0:0:0:0:0:0:1','2022-06-02 11:30:03'),(88,'95be5694-7afb-4d8e-b8ec-4dfd28192b72','anonymousUser','/api/authenticate','0:0:0:0:0:0:0:1','2022-06-02 11:33:17'),(89,'55cc2731-2990-4ef1-a38c-8500d94e30f0','admin','/api/account','0:0:0:0:0:0:0:1','2022-06-02 11:33:18'),(90,'abb9f7b7-a05d-436f-947b-5d71e6829510','admin','/api/account','0:0:0:0:0:0:0:1','2022-06-02 11:33:20'),(91,'6a5016d0-fcd4-422c-98a3-c8755cc1f4a5','admin','/api/menus/tree','0:0:0:0:0:0:0:1','2022-06-02 11:33:20'),(92,'58e11bf8-7a8a-47ae-b3bd-27c33f6588e2','admin','/api/account','0:0:0:0:0:0:0:1','2022-06-02 11:33:20'),(93,'49cb16ce-b83a-42c3-b9fc-387197e0741e','admin','/api/account','0:0:0:0:0:0:0:1','2022-06-02 11:33:20'),(94,'08d6fecd-c3f4-428d-9186-99a85b506e9e','admin','/api/menus','0:0:0:0:0:0:0:1','2022-06-02 11:33:23'),(95,'63a4dc37-a575-4c62-804d-e8c03ed8ae39','admin','/api/ui-components','0:0:0:0:0:0:0:1','2022-06-02 11:33:24'),(96,'0777ab44-2f8f-45c3-a150-b49ace40a75c','admin','/api/ui-tool-buttons','0:0:0:0:0:0:0:1','2022-06-02 11:33:25'),(97,'da002029-e09c-4921-ac02-72628ff7f953','admin','/api/leave-types','0:0:0:0:0:0:0:1','2022-06-02 11:33:33'),(98,'03be45e7-1fac-4ca1-92cf-a9445d1343d8','admin','/api/data-permissions','0:0:0:0:0:0:0:1','2022-06-02 11:33:35'),(99,'4bdad834-7259-4e14-afdd-631d04ca7531','admin','/api/roles/tree','0:0:0:0:0:0:0:1','2022-06-02 11:33:37'),(100,'747cd28b-1889-4c1f-bf83-12931651f193','admin','/api/menus/tree','0:0:0:0:0:0:0:1','2022-06-02 11:33:37'),(101,'9ece8bce-b939-4fac-a1a6-4e2bcc17beeb','admin','/api/role-menu-tool-buttons/menu/by/role/ROLE_ADMIN','0:0:0:0:0:0:0:1','2022-06-02 11:33:39'),(102,'f0e33ee1-d869-4f7c-98aa-65ba6fc2dc82','admin','/api/role-menu-tool-buttons/toolbutton/by/role/ROLE_ADMIN/menu/6','0:0:0:0:0:0:0:1','2022-06-02 11:33:40'),(103,'8038e6fd-0680-4266-bfbb-9eeb2cbc849d','admin','/api/roles/tree','0:0:0:0:0:0:0:1','2022-06-02 11:33:42'),(104,'36c58f52-cda4-4652-a643-fc758c18a256','admin','/api/menus/tree','0:0:0:0:0:0:0:1','2022-06-02 11:33:42'),(105,'fc480505-d429-4efc-9124-f8316372f8ec','admin','/api/role-menus/menu/by/role/ROLE_ADMIN','0:0:0:0:0:0:0:1','2022-06-02 11:33:43'),(106,'193f0ed0-264f-4c73-b5c7-baeec2c693e8','admin','/api/menus/tree','0:0:0:0:0:0:0:1','2022-06-02 11:33:46'),(107,'60823269-9394-4544-b791-d9c20c7f4394','admin','/api/roles/tree','0:0:0:0:0:0:0:1','2022-06-02 11:33:46'),(108,'aee196fb-107b-45ec-abed-90eb912001e7','admin','/api/roles/tree','0:0:0:0:0:0:0:1','2022-06-02 11:33:46'),(109,'70291c8b-a5db-4cd9-b212-f079528efc04','admin','/api/menus/tree','0:0:0:0:0:0:0:1','2022-06-02 11:33:46'),(110,'d2ca4b81-04a2-4143-95a6-4bf9a598ed63','admin','/api/data-permissions/tree','0:0:0:0:0:0:0:1','2022-06-02 11:33:46'),(111,'75c6f006-e4e8-4b48-a389-0dc4bdbeedf2','admin','/api/data-permissions-rels/menu/permission/by/role/ROLE_ADMIN','0:0:0:0:0:0:0:1','2022-06-02 11:33:48'),(112,'3b29cc18-b713-43b8-a6f8-6d91e3f907fe','admin','/api/data-permissions-rels/menu/permission/by/role/ROLE_ADMIN/menu/4','0:0:0:0:0:0:0:1','2022-06-02 11:33:50'),(113,'f94f2067-43e7-4483-a7a7-07eb8db2f44c','admin','/api/data-permissions-rels/menu/permission/by/role/ROLE_ADMIN/menu/6','0:0:0:0:0:0:0:1','2022-06-02 11:33:51'),(114,'cd620f0f-8d8d-4de6-8867-6cc6cf5a766e','admin','/api/data-permissions-rels/menu/permission/by/role/ROLE_ADMIN/menu/4','0:0:0:0:0:0:0:1','2022-06-02 11:33:54'),(115,'285c3c3a-e0d7-4c54-8dbe-b8c96d86b3e9','admin','/api/data-permissions','0:0:0:0:0:0:0:1','2022-06-02 11:34:00'),(116,'9f4f8487-50de-4707-b4a6-43b2305de2e2','admin','/api/data-permissions','0:0:0:0:0:0:0:1','2022-06-02 11:34:24'),(117,'f2475eab-d948-4ce2-8458-06f8f295fe5e','admin','/api/menus/tree','0:0:0:0:0:0:0:1','2022-06-02 11:34:31'),(118,'31c232c0-f229-4f87-9b2f-689b13b2a4b8','admin','/api/roles/tree','0:0:0:0:0:0:0:1','2022-06-02 11:34:31'),(119,'36919725-f0a9-455c-9aa6-00f4f6dedea3','admin','/api/roles/tree','0:0:0:0:0:0:0:1','2022-06-02 11:34:33'),(120,'ca787b0b-d6dc-4256-bc10-2dc74317ea1b','admin','/api/data-permissions/tree','0:0:0:0:0:0:0:1','2022-06-02 11:34:33'),(121,'326b9e19-399a-4f41-8d6d-83aa2b5555a5','admin','/api/menus/tree','0:0:0:0:0:0:0:1','2022-06-02 11:34:33'),(122,'0e4b5ab7-4dc7-4a56-a9c7-cf38cdae127f','admin','/api/data-permissions','0:0:0:0:0:0:0:1','2022-06-02 11:34:34'),(123,'6c70c5c3-ffba-4661-9a8f-275676171021','admin','/api/roles/tree','0:0:0:0:0:0:0:1','2022-06-02 11:34:58'),(124,'90f8aa09-9c03-4553-8d09-9f817487d1f9','admin','/api/menus/tree','0:0:0:0:0:0:0:1','2022-06-02 11:34:58'),(125,'b940b46d-d6e2-4647-b97a-4df74bdc230d','admin','/api/data-permissions/tree','0:0:0:0:0:0:0:1','2022-06-02 11:34:58'),(126,'3c4d5f07-1522-441e-ab38-d7443e4fb526','admin','/api/data-permissions-rels/menu/permission/by/role/ROLE_ADMIN','0:0:0:0:0:0:0:1','2022-06-02 11:35:02'),(127,'e595efb4-6494-4093-a183-2fc720b520ca','admin','/api/data-permissions-rels/menu/permission/by/role/ROLE_ADMIN/menu/4','0:0:0:0:0:0:0:1','2022-06-02 11:35:03'),(128,'f8133c8e-6445-413a-872f-5dc8ceadceed','admin','/api/data-permissions-rels/menu/permission/by/role/ROLE_ADMIN/menu/6','0:0:0:0:0:0:0:1','2022-06-02 11:35:05'),(129,'dec89d84-49e0-4f9c-a283-0faf0b333190','admin','/api/menus/tree','0:0:0:0:0:0:0:1','2022-06-02 11:35:07'),(130,'21face46-06b4-4f80-9599-ea64040c2ca9','admin','/api/roles/tree','0:0:0:0:0:0:0:1','2022-06-02 11:35:07'),(131,'abaee284-0cb9-4de2-a976-70e7e3e42d8f','admin','/api/role-menu-tool-buttons/menu/by/role/ROLE_ADMIN','0:0:0:0:0:0:0:1','2022-06-02 11:35:10'),(132,'48b9922f-89c5-48f7-9dfe-4d7c98dc9fde','admin','/api/role-menu-tool-buttons/menu/by/role/ROLE_USER','0:0:0:0:0:0:0:1','2022-06-02 11:35:11'),(133,'2bbce18c-5a02-4073-9fee-8cc9266b9cd2','admin','/api/role-menu-tool-buttons/menu/by/role/ROLE_ADMIN','0:0:0:0:0:0:0:1','2022-06-02 11:35:12'),(134,'6836c678-7927-4001-a8f0-8cbeef22aad9','admin','/api/leave-types','0:0:0:0:0:0:0:1','2022-06-02 11:35:16'),(135,'f94744fb-1cec-4668-a3b2-614943aba16a','admin','/api/menus','0:0:0:0:0:0:0:1','2022-06-02 11:35:19'),(136,'49c8032d-f142-4e8f-986e-809416c2572b','admin','/api/ui-components','0:0:0:0:0:0:0:1','2022-06-02 11:35:19'),(137,'829a5438-d392-4101-a1b9-a8ce9191a5f3','admin','/api/ui-tool-buttons','0:0:0:0:0:0:0:1','2022-06-02 11:35:20'),(138,'c7736787-e442-455b-9a0c-fd82e98d264e','admin','/api/roles/tree','0:0:0:0:0:0:0:1','2022-06-02 11:35:23'),(139,'fc3b6d3d-afa2-463f-8f8f-1f2ff8e571f8','admin','/api/menus/tree','0:0:0:0:0:0:0:1','2022-06-02 11:35:23'),(140,'18a44373-cf28-440d-a1f9-e4215eae3f08','admin','/api/roles/tree','0:0:0:0:0:0:0:1','2022-06-02 11:35:23'),(141,'a50e9179-26c7-49e2-a89d-901392383e25','admin','/api/data-permissions/tree','0:0:0:0:0:0:0:1','2022-06-02 11:35:23'),(142,'eb25815c-25da-4621-b097-d811c8902f97','admin','/api/menus/tree','0:0:0:0:0:0:0:1','2022-06-02 11:35:23'),(143,'3a72f1a7-22d3-40ce-97e4-af5a29d53287','admin','/api/menus/tree','0:0:0:0:0:0:0:1','2022-06-02 11:35:25'),(144,'0a6e3b0f-6d7a-46e9-813a-096d1598225c','admin','/api/roles/tree','0:0:0:0:0:0:0:1','2022-06-02 11:35:25'),(145,'0bc6b65e-4790-4366-8914-7e5a4ee90d94','admin','/api/online/users','0:0:0:0:0:0:0:1','2022-06-02 11:35:31'),(146,'fc9ea332-7776-44b1-a060-e9fbac9dbc2f','admin','/api/request-loggings','0:0:0:0:0:0:0:1','2022-06-02 11:35:36'),(147,'514019b3-d35f-483c-b3fb-9ec46cd5f07e','admin','/api/slow-sql-loggings','0:0:0:0:0:0:0:1','2022-06-02 11:35:59'),(148,'6404989f-713d-4257-8d69-fa9e63dc71cf','admin','/api/request-loggings','0:0:0:0:0:0:0:1','2022-06-02 11:36:03'),(149,'aeb84cdd-0d2a-45fd-82c0-9437be851f81','admin','/api/account','0:0:0:0:0:0:0:1','2022-06-02 11:37:12'),(150,'2da9fc16-e2af-4d20-8446-914b1516389b','admin','/api/account','0:0:0:0:0:0:0:1','2022-06-02 11:37:12'),(151,'74c1b23d-db76-4ae2-9d27-9892e8f1a670','admin','/api/account','0:0:0:0:0:0:0:1','2022-06-02 11:37:12'),(152,'6fa33d17-cfb4-4fdb-a5d5-e065e6b90bcf','admin','/api/menus/tree','0:0:0:0:0:0:0:1','2022-06-02 11:37:12'),(153,'f624edc5-fdd1-418f-b15a-775399bdd607','admin','/api/request-loggings','0:0:0:0:0:0:0:1','2022-06-02 11:37:13'),(154,'e5afdf39-1ffe-4dd6-9fc3-048d4c542079','admin','/api/menus','0:0:0:0:0:0:0:1','2022-06-02 11:37:15'),(155,'2610047d-cdfa-44b5-8f92-5f1c780b6a5b','admin','/api/ui-components','0:0:0:0:0:0:0:1','2022-06-02 11:37:16'),(156,'6848a072-9097-495a-9991-d73389588ec6','admin','/api/ui-tool-buttons','0:0:0:0:0:0:0:1','2022-06-02 11:37:17'),(157,'68fa1995-3e34-492c-a305-26e248ca2563','admin','/api/slow-sql-loggings','0:0:0:0:0:0:0:1','2022-06-02 11:37:21'),(158,'2c9fbb3a-6106-48cf-a96e-fa75e2112fd7','admin','/api/menus','0:0:0:0:0:0:0:1','2022-06-02 11:38:41'),(159,'b42e994d-3ed1-4569-9758-b0efe2bd8291','admin','/api/menus','0:0:0:0:0:0:0:1','2022-06-02 11:38:51'),(160,'3d4815df-2dae-4d4a-ae5f-065defcef9c8','admin','/api/menus','0:0:0:0:0:0:0:1','2022-06-02 11:39:13'),(161,'28a99b81-ec36-4cae-8bc7-fec0c8527efd','admin','/api/online/users','0:0:0:0:0:0:0:1','2022-06-02 11:39:37'),(162,'009685df-159d-4eb5-a3a0-e0a2a6d723b8','admin','/api/menus','0:0:0:0:0:0:0:1','2022-06-02 11:39:41'),(163,'bf5700bd-bef4-4888-b01f-74907f3ea3f2','admin','/api/menus','0:0:0:0:0:0:0:1','2022-06-02 11:39:47'),(164,'4e18abbe-3b30-466e-92db-5a34d45be1eb','admin','/api/menus/29','0:0:0:0:0:0:0:1','2022-06-02 11:41:32'),(165,'648e5ece-c038-447e-9d04-d0f926aa02fb','admin','/api/menus/29','0:0:0:0:0:0:0:1','2022-06-02 11:41:43'),(166,'b8e14469-38a8-4fd3-9f63-bfdf0e19555b','admin','/api/menus','0:0:0:0:0:0:0:1','2022-06-02 11:41:43'),(167,'70aa49c4-a2df-48b8-81c0-c6e925467976','admin','/api/menus','0:0:0:0:0:0:0:1','2022-06-02 11:42:00'),(168,'50d85b76-eaf4-4e7a-8d13-6922c89a859b','admin','/api/menus','0:0:0:0:0:0:0:1','2022-06-02 11:42:15'),(169,'91e4aad6-ca33-4377-a781-d8db7d82106c','admin','/api/menus','0:0:0:0:0:0:0:1','2022-06-02 11:42:20'),(170,'88ffaccb-c199-4647-839b-cb04414b21ca','admin','/api/menus','0:0:0:0:0:0:0:1','2022-06-02 11:43:16'),(171,'73fdf838-8eb4-4bbf-bac4-4896c13a9e3c','admin','/api/menus','0:0:0:0:0:0:0:1','2022-06-02 11:43:17'),(172,'516558e0-d1a8-46b9-9bed-f471de2bac0d','admin','/api/account','0:0:0:0:0:0:0:1','2022-06-02 11:43:19'),(173,'f546d80b-3e3c-448b-b761-1dea72c083f0','admin','/api/account','0:0:0:0:0:0:0:1','2022-06-02 11:43:19'),(174,'82511514-6040-40db-888b-d77e93f273a6','admin','/api/menus/tree','0:0:0:0:0:0:0:1','2022-06-02 11:43:19'),(175,'932a0eda-f463-4149-a684-b9cd8af299a2','admin','/api/account','0:0:0:0:0:0:0:1','2022-06-02 11:43:19'),(176,'16928b0d-c28f-4caa-a724-88389dfe1da7','admin','/api/menus','0:0:0:0:0:0:0:1','2022-06-02 11:43:19'),(177,'13501898-ac0c-4ec3-9f74-c973a1a08bbe','admin','/api/request-loggings','0:0:0:0:0:0:0:1','2022-06-02 11:43:24'),(178,'3a8c1c97-f5a7-4194-8641-d109cbc76010','admin','/api/menus','0:0:0:0:0:0:0:1','2022-06-02 11:43:35'),(179,'b4fb1308-2022-43b1-af9a-865869a6bdd5','admin','/api/menus','0:0:0:0:0:0:0:1','2022-06-02 11:43:39'),(180,'7b12d201-2546-4af2-a067-c2794e7371a3','admin','/api/menus/32','0:0:0:0:0:0:0:1','2022-06-02 11:43:44'),(181,'79752227-a341-4d59-a126-21d3f97cf003','admin','/api/menus/32','0:0:0:0:0:0:0:1','2022-06-02 11:43:48'),(182,'e6fc038c-d84c-40b3-b5e0-18a355d040dc','admin','/api/menus','0:0:0:0:0:0:0:1','2022-06-02 11:43:49'),(183,'970a1e58-a540-479e-aeac-fa4fbd9b05e1','admin','/api/account','0:0:0:0:0:0:0:1','2022-06-02 11:43:51'),(184,'65e716b3-d55b-4461-926d-28d3cb442e10','admin','/api/account','0:0:0:0:0:0:0:1','2022-06-02 11:43:51'),(185,'36ba5896-e52b-42fb-a944-5711473e04ba','admin','/api/menus/tree','0:0:0:0:0:0:0:1','2022-06-02 11:43:51'),(186,'a6b35fea-0865-4107-b87e-807fa567c6e1','admin','/api/account','0:0:0:0:0:0:0:1','2022-06-02 11:43:51'),(187,'14fe120c-f6d6-4f7e-bb43-4a85e2ca5519','admin','/api/menus','0:0:0:0:0:0:0:1','2022-06-02 11:43:52'),(188,'450a5f55-e4c0-42da-be22-ce22e8cbd9b2','admin','/api/slow-sql-loggings','0:0:0:0:0:0:0:1','2022-06-02 11:43:54'),(189,'275d0e2b-6e1d-4b09-a8e4-c42b9e5930cf','admin','/api/request-loggings','0:0:0:0:0:0:0:1','2022-06-02 11:44:13'),(190,'4b6f8a2a-93ed-4b35-b46e-a0559bbfa780','admin','/api/slow-sql-loggings','0:0:0:0:0:0:0:1','2022-06-02 11:44:25'),(191,'8d5e06a4-37cf-40f6-a18f-010c399c6969','admin','/api/request-loggings','0:0:0:0:0:0:0:1','2022-06-02 11:44:26'),(192,'a881f6ed-3fdb-4667-a61f-92b94afcc903','admin','/api/online/users','0:0:0:0:0:0:0:1','2022-06-02 11:44:40'),(193,'fe3df372-04c8-43ed-bdaa-220b0c0ddd85','admin','/api/request-loggings','0:0:0:0:0:0:0:1','2022-06-02 11:44:42'),(194,'027930a3-64c8-4e4d-8ec1-35c3e375db4c','admin','/api/slow-sql-loggings','0:0:0:0:0:0:0:1','2022-06-02 11:44:43'),(195,'3258fddb-eb76-43b0-be4e-f7e3db8f9324','admin','/api/account','0:0:0:0:0:0:0:1','2022-06-02 11:45:45'),(196,'d564d7ff-d560-44b8-99ee-99e85f5b3d70','admin','/api/menus/tree','0:0:0:0:0:0:0:1','2022-06-02 11:45:45'),(197,'e60ab957-9a89-41c3-8dac-a438248d628d','admin','/api/account','0:0:0:0:0:0:0:1','2022-06-02 11:45:45'),(198,'31997642-b247-4e3d-9a26-3a0fc98826d2','admin','/api/account','0:0:0:0:0:0:0:1','2022-06-02 11:45:45'),(199,'8897216a-3801-4d57-b9fe-7212d955d843','admin','/api/slow-sql-loggings','0:0:0:0:0:0:0:1','2022-06-02 11:45:46'),(200,'d2381fb0-217d-49ef-a083-7cb7bf557fc7','admin','/api/request-loggings','0:0:0:0:0:0:0:1','2022-06-02 11:45:49'),(201,'35d44fb8-d8d0-494b-b664-a64c2257967a','admin','/api/online/users','0:0:0:0:0:0:0:1','2022-06-02 11:45:50'),(202,'1f001bbe-b49e-42b9-9d87-ddeeaa86ddd0','admin','/api/request-loggings','0:0:0:0:0:0:0:1','2022-06-02 11:45:51'),(203,'af1b3804-94bb-4932-816e-d195f8dcc99a','admin','/api/slow-sql-loggings','0:0:0:0:0:0:0:1','2022-06-02 11:45:52'),(204,'4e72f423-f21f-4eb9-a401-0bb0128a8289','admin','/api/request-loggings','0:0:0:0:0:0:0:1','2022-06-02 11:45:53'),(205,'35a9e2a1-3077-49a7-9397-23086a7fcedb','admin','/api/slow-sql-loggings','0:0:0:0:0:0:0:1','2022-06-02 11:45:59'),(206,'c169abf9-c379-49ae-8be4-eebeaf36178f','admin','/api/request-loggings','0:0:0:0:0:0:0:1','2022-06-02 11:46:02'),(207,'b235dca2-5152-4b3d-b7c0-c545eaa38ea5','admin','/api/data-permissions','0:0:0:0:0:0:0:1','2022-06-02 11:46:09'),(208,'1f7e3c12-36c9-45f3-8cee-9eef68fe3ad3','admin','/api/roles/tree','0:0:0:0:0:0:0:1','2022-06-02 11:46:09'),(209,'4c20b6e3-0c3f-4dae-812a-d2b1fb25b60f','admin','/api/menus/tree','0:0:0:0:0:0:0:1','2022-06-02 11:46:09'),(210,'4348571d-db76-4de5-919b-ce065fdfa894','admin','/api/data-permissions/tree','0:0:0:0:0:0:0:1','2022-06-02 11:46:09'),(211,'fa3301d3-526d-42da-ae14-09211dc2223d','admin','/api/leave-types','0:0:0:0:0:0:0:1','2022-06-02 11:46:12'),(212,'b81fe16e-01e3-4309-a374-7e0ec06585c6','admin','/api/menus','0:0:0:0:0:0:0:1','2022-06-02 11:46:16'),(213,'b1196b1c-27b4-4758-88a5-bb04f8f2ce1a','admin','/api/ui-components','0:0:0:0:0:0:0:1','2022-06-02 11:46:17'),(214,'580c41d6-dcda-40d9-ae13-8cfa376abba4','admin','/api/ui-tool-buttons','0:0:0:0:0:0:0:1','2022-06-02 11:46:18'),(215,'0a895413-ed65-4787-9047-371234e12eba','admin','/api/ui-components/menu/0000','0:0:0:0:0:0:0:1','2022-06-02 11:46:21'),(216,'382591bd-a3e9-4a62-88d7-3d9632a9c063','admin','/api/examples/page/1/size/5','0:0:0:0:0:0:0:1','2022-06-02 11:46:21'),(217,'bcd96bcf-e4ef-4a9e-8d3d-4b78aa6f4efb','admin','/api/ui-editforms/menu/0000','0:0:0:0:0:0:0:1','2022-06-02 11:46:21'),(218,'53b2f969-a63c-4e13-8b69-cfdd15bcfeee','admin','/api/ui-tables/menu/0000','0:0:0:0:0:0:0:1','2022-06-02 11:46:21'),(219,'7644bf68-bfb6-4d84-97f6-becf7ad563aa','admin','/api/ui-tool-buttons/menu/0000','0:0:0:0:0:0:0:1','2022-06-02 11:46:21'),(220,'46f570ab-5cfe-4e72-a329-958dc4d7f062','admin','/api/ui-tabs/menu/0000','0:0:0:0:0:0:0:1','2022-06-02 11:46:21'),(221,'c4e09f7c-4772-4fe5-8f05-e6821c0cc4a2','admin','/api/ui-components/menu/5','0:0:0:0:0:0:0:1','2022-06-02 11:46:22'),(222,'f6b8646c-6e99-4577-81c7-22d7720b6a83','admin','/api/ui-editforms/menu/5','0:0:0:0:0:0:0:1','2022-06-02 11:46:22'),(223,'8e5c5e77-bad2-47f8-a36e-c67abfad70f9','admin','/api/ui-tool-buttons/menu/5','0:0:0:0:0:0:0:1','2022-06-02 11:46:22'),(224,'7762c6d9-bae7-416f-8ac5-5fe4db7b96ac','admin','/api/ui-components/menu/6','0:0:0:0:0:0:0:1','2022-06-02 11:46:23'),(225,'358c17bd-5a61-41ff-9a4f-07000cd5514f','admin','/api/leave-slips','0:0:0:0:0:0:0:1','2022-06-02 11:46:23'),(226,'84bcdfd6-f162-41be-b897-cfc71912047d','admin','/api/ui-tables/menu/6','0:0:0:0:0:0:0:1','2022-06-02 11:46:23'),(227,'0e1cc7e4-02bb-40a5-9f7b-3fc1643a75e0','admin','/api/ui-tool-buttons/menu/6','0:0:0:0:0:0:0:1','2022-06-02 11:46:23'),(228,'a15e339d-0e35-4108-af60-f32eb49ce684','admin','/api/online/users','0:0:0:0:0:0:0:1','2022-06-02 11:46:29'),(229,'d44d20ac-eeb7-4fdc-8d87-aa08393695ba','admin','/api/request-loggings','0:0:0:0:0:0:0:1','2022-06-02 11:46:31'),(230,'2cde10d0-f4cd-43e1-922d-a68ea66d7867','admin','/api/slow-sql-loggings','0:0:0:0:0:0:0:1','2022-06-02 11:46:33'),(231,'86919ef6-d65e-4ffb-938d-eb592002ee12','admin','/api/request-loggings','0:0:0:0:0:0:0:1','2022-06-02 11:46:34'),(232,'e25686e6-a377-4875-ac1c-652fd01d4106','admin','/api/online/users','0:0:0:0:0:0:0:1','2022-06-02 11:46:35'),(233,'0439cb9d-6e02-4f70-b8c8-69b819d846ef','admin','/api/request-loggings','0:0:0:0:0:0:0:1','2022-06-02 11:46:36'),(234,'d89a7656-fd01-450c-aa8a-16913ce85440','admin','/api/online/users','0:0:0:0:0:0:0:1','2022-06-02 11:46:46'),(235,'cecae4eb-251c-4935-a81a-c7faf99e11ec','admin','/api/request-loggings','0:0:0:0:0:0:0:1','2022-06-02 11:46:47'),(236,'2957063b-3eb4-4697-beac-8cb6d6d91e9a','admin','/api/online/users','0:0:0:0:0:0:0:1','2022-06-02 11:47:00'),(237,'c9829d26-5a62-4545-b737-fbe1b1a9af5a','admin','/api/request-loggings','0:0:0:0:0:0:0:1','2022-06-02 11:47:00'),(238,'c7cf2c18-e3f4-46d8-97c5-ff98be5838a4','admin','/api/slow-sql-loggings','0:0:0:0:0:0:0:1','2022-06-02 11:47:01'),(239,'cc78e700-d2f9-4eb0-9d97-bc2c8f596645','admin','/api/request-loggings','0:0:0:0:0:0:0:1','2022-06-02 11:47:21'),(240,'918bccdf-ea5e-4896-b6b9-d9b9f05324f2','admin','/api/ui-components','0:0:0:0:0:0:0:1','2022-06-02 11:51:52'),(241,'edd53a99-2268-4ff0-a1b2-59643709b0e8','admin','/api/ui-editforms','0:0:0:0:0:0:0:1','2022-06-02 11:51:57'),(242,'648bca9d-939e-4769-8822-5957c60da12d','admin','/api/ui-tabs','0:0:0:0:0:0:0:1','2022-06-02 11:51:59'),(243,'b0473d10-da36-4f16-83f8-780cdf72f787','admin','/api/ui-queryforms','0:0:0:0:0:0:0:1','2022-06-02 11:52:00'),(244,'993cd9b6-9095-4ac4-9450-956a0c9adb60','admin','/api/ui-components','0:0:0:0:0:0:0:1','2022-06-02 11:52:02'),(245,'a1aaaa79-74ce-4926-b0b6-21cdceb65a39','admin','/api/ui-tool-buttons','0:0:0:0:0:0:0:1','2022-06-02 11:52:03'),(246,'d6472b23-2519-4a5e-8c80-0b9e8f0695f1','admin','/api/ui-tables','0:0:0:0:0:0:0:1','2022-06-02 11:52:04'),(247,'b63b91b7-36fc-4eb0-aeb7-ee90d9db245b','admin','/api/leave-types','0:0:0:0:0:0:0:1','2022-06-02 11:52:18'),(248,'c1ededa4-c2e0-46ea-960d-9216fe0c76d9','admin','/api/roles/tree','0:0:0:0:0:0:0:1','2022-06-02 11:52:20'),(249,'be1d0cd4-7bf8-4f63-b95a-00340394dadb','admin','/api/data-permissions/tree','0:0:0:0:0:0:0:1','2022-06-02 11:52:20'),(250,'ceb7d335-47c8-406e-949f-d06963e6db74','admin','/api/menus/tree','0:0:0:0:0:0:0:1','2022-06-02 11:52:20'),(251,'44b6ec8e-6749-4fc4-b63e-ed4c4d5a583c','admin','/api/menus/tree','0:0:0:0:0:0:0:1','2022-06-02 11:52:21'),(252,'c7999bec-9fd8-462c-a414-c96b5b7078cb','admin','/api/roles/tree','0:0:0:0:0:0:0:1','2022-06-02 11:52:21'),(253,'47685c1f-11e7-4916-8f2d-4def2dcb8bfd','admin','/api/online/users','0:0:0:0:0:0:0:1','2022-06-02 11:52:27'),(254,'e339076f-1007-4141-aa19-1d71934c250f','admin','/api/request-loggings','0:0:0:0:0:0:0:1','2022-06-02 11:52:28'),(255,'dcd9399f-bf54-4380-a54f-212d9e6466e9','admin','/api/menus','0:0:0:0:0:0:0:1','2022-06-02 11:53:45'),(256,'97239dd9-a99e-4478-841a-d0b54f317a5f','admin','/api/account','0:0:0:0:0:0:0:1','2022-06-02 11:55:27'),(257,'5b668e96-52b4-47f9-9ff7-5c66f763dd11','admin','/api/account','0:0:0:0:0:0:0:1','2022-06-02 11:55:27'),(258,'9d2e7248-0cc5-4ca4-8cd7-87767fe4d368','admin','/api/menus/tree','0:0:0:0:0:0:0:1','2022-06-02 11:55:27'),(259,'1f9e4bd3-346a-4318-a676-93e094a6c6da','admin','/api/account','0:0:0:0:0:0:0:1','2022-06-02 11:55:27'),(260,'3069c8b7-a32f-46f1-b34c-b8e02c4f1150','admin','/api/account','0:0:0:0:0:0:0:1','2022-06-02 11:55:28'),(261,'b3ae6885-b0d0-49b4-8369-cb6726d39464','admin','/api/menus/tree','0:0:0:0:0:0:0:1','2022-06-02 11:55:28'),(262,'49f7c70a-92cf-4951-ba55-5f592e2f4010','admin','/api/account','0:0:0:0:0:0:0:1','2022-06-02 11:55:28'),(263,'82b75270-831a-4af8-b8bf-ebcae570c117','admin','/api/account','0:0:0:0:0:0:0:1','2022-06-02 11:55:28'),(264,'4c1021dc-1c67-4630-b71c-1c907118e8b7','admin','/api/menus','0:0:0:0:0:0:0:1','2022-06-02 11:55:28'),(265,'fda73200-61e6-467f-ac94-9a8a7857d017','admin','/api/account','0:0:0:0:0:0:0:1','2022-06-02 11:55:35'),(266,'eee58efb-3c87-4f4d-b34c-c7f0dea98d3b','admin','/api/account','0:0:0:0:0:0:0:1','2022-06-02 11:55:35'),(267,'c7118b53-402e-4e9c-a4cc-fa484cf7ddc5','admin','/api/menus/tree','0:0:0:0:0:0:0:1','2022-06-02 11:55:35'),(268,'4d6818b7-fde9-4019-b7e7-3a9d5441e743','admin','/api/account','0:0:0:0:0:0:0:1','2022-06-02 11:55:35'),(269,'dcbe015a-c82c-43a5-9336-f1adfb02a24f','admin','/api/account','0:0:0:0:0:0:0:1','2022-06-02 11:55:35'),(270,'34e23225-57db-42a4-887d-5cdd408f87d9','admin','/api/account','0:0:0:0:0:0:0:1','2022-06-02 11:55:35'),(271,'7ed91cd2-f99a-497c-85de-67e979ff5c96','admin','/api/menus/tree','0:0:0:0:0:0:0:1','2022-06-02 11:55:35'),(272,'72fd81e0-a66b-4d0b-b58f-d217ed3e6412','admin','/api/account','0:0:0:0:0:0:0:1','2022-06-02 11:55:36'),(273,'09540f12-506a-4017-aa49-802eb5dd1d10','admin','/api/menus','0:0:0:0:0:0:0:1','2022-06-02 11:55:36'),(274,'4a770bd5-ad9a-4209-b891-09b0f12af322','admin','/api/account','0:0:0:0:0:0:0:1','2022-06-02 11:56:24'),(275,'6713f8e3-115b-4faf-a980-fa075d13b722','admin','/api/account','0:0:0:0:0:0:0:1','2022-06-02 11:56:24'),(276,'d16e5bf3-180d-42e3-9fc2-56582ddcd072','admin','/api/menus/tree','0:0:0:0:0:0:0:1','2022-06-02 11:56:24'),(277,'000902a9-2ebd-4ce3-8eb4-dc2603148b1e','admin','/api/account','0:0:0:0:0:0:0:1','2022-06-02 11:56:24'),(278,'0ea66c40-1c46-4ac9-976e-778e4f963505','admin','/api/menus','0:0:0:0:0:0:0:1','2022-06-02 11:56:25'),(279,'fdc64799-7455-4c4c-be8f-5aa39e11152f','admin','/api/account','0:0:0:0:0:0:0:1','2022-06-02 11:56:26'),(280,'3aab88ae-cafe-4c06-994a-833dd77dfec4','admin','/api/account','0:0:0:0:0:0:0:1','2022-06-02 11:56:26'),(281,'e6114e49-4c9c-41e1-8f6b-d4abe4bb46a2','admin','/api/menus/tree','0:0:0:0:0:0:0:1','2022-06-02 11:56:26'),(282,'2c85ee9a-29f4-48f9-90ae-454b8308e070','admin','/api/account','0:0:0:0:0:0:0:1','2022-06-02 11:56:27'),(283,'7d99b21a-ce26-410e-8f29-015949f3dbcc','admin','/api/menus','0:0:0:0:0:0:0:1','2022-06-02 11:56:27'),(284,'f02a673f-4c90-4e6f-a01c-a7e5fae50594','admin','/api/account','0:0:0:0:0:0:0:1','2022-06-02 11:56:30'),(285,'5b8e7af1-3884-42da-a315-e871bb3db13d','admin','/api/menus/tree','0:0:0:0:0:0:0:1','2022-06-02 11:56:30'),(286,'8194f0cd-9266-4c81-9b71-78474eae8d93','admin','/api/account','0:0:0:0:0:0:0:1','2022-06-02 11:56:30'),(287,'40840760-3a2b-4a4e-83b5-2423e6d4f6a6','admin','/api/account','0:0:0:0:0:0:0:1','2022-06-02 11:56:30'),(288,'8a3381e8-1873-47a5-b9c0-28fee9a1e697','admin','/api/menus','0:0:0:0:0:0:0:1','2022-06-02 11:56:31'),(289,'8db8a38e-e915-4a2e-9915-9b7dcc12019a','admin','/api/account','0:0:0:0:0:0:0:1','2022-06-02 11:56:40'),(290,'1b293ccc-c1bf-4b33-bd84-2fc74b340923','admin','/api/account','0:0:0:0:0:0:0:1','2022-06-02 11:56:41'),(291,'f51eb51c-da5f-4df0-8c55-0c0224410eb9','admin','/api/menus/tree','0:0:0:0:0:0:0:1','2022-06-02 11:56:41'),(292,'105b1360-875e-4789-9356-0c077feeef94','admin','/api/account','0:0:0:0:0:0:0:1','2022-06-02 11:56:41'),(293,'e2bd9a42-fce2-4feb-afea-9aab76d18e51','admin','/api/menus','0:0:0:0:0:0:0:1','2022-06-02 11:56:41'),(294,'47fe270a-cb31-415d-9e4f-affbce4410be','admin','/api/account','0:0:0:0:0:0:0:1','2022-06-02 11:57:04'),(295,'30525bcf-3408-4a89-8d16-3c19a8fd55f8','admin','/api/menus/tree','0:0:0:0:0:0:0:1','2022-06-02 11:57:04'),(296,'f9d7c0da-7618-4c98-a062-c3d824a16fa1','admin','/api/account','0:0:0:0:0:0:0:1','2022-06-02 11:57:04'),(297,'3aa183a5-bbcc-44ae-99c9-92c67085445b','admin','/api/menus','0:0:0:0:0:0:0:1','2022-06-02 11:57:05'),(298,'2f008999-7103-4904-94f4-76a93c58cd1f','admin','/api/account','0:0:0:0:0:0:0:1','2022-06-02 11:57:07'),(299,'ac69ba96-a294-4c0e-bf6e-a3c32002af5c','admin','/api/account','0:0:0:0:0:0:0:1','2022-06-02 11:57:07'),(300,'23817da8-7c2b-45d1-b5b4-9c100cb94450','admin','/api/menus/tree','0:0:0:0:0:0:0:1','2022-06-02 11:57:07'),(301,'eaabdfe8-d3c9-40c0-8438-478ab6f2b61c','admin','/api/account','0:0:0:0:0:0:0:1','2022-06-02 11:57:08'),(302,'c6f2e0f8-0729-4a62-b9fa-6736fec6eb57','admin','/api/menus','0:0:0:0:0:0:0:1','2022-06-02 11:57:08'),(303,'1369bd18-31fe-43d1-a84d-94a07ac1d938','admin','/api/account','0:0:0:0:0:0:0:1','2022-06-02 11:57:10'),(304,'1a747851-0a2e-4974-8ad6-62efe401d5e1','admin','/api/account','0:0:0:0:0:0:0:1','2022-06-02 11:57:10'),(305,'c85e8b10-057c-4616-bec1-34918c383f52','admin','/api/menus/tree','0:0:0:0:0:0:0:1','2022-06-02 11:57:10'),(306,'5d86a27b-4820-4c4e-bfda-2e3b41a7ca80','admin','/api/account','0:0:0:0:0:0:0:1','2022-06-02 11:57:10'),(307,'c667b988-5264-4375-a1bc-0f3c1f59ccde','admin','/api/account','0:0:0:0:0:0:0:1','2022-06-02 11:57:10'),(308,'b1a257b0-d48d-44e8-bfa1-b9a94e2ec8a2','admin','/api/account','0:0:0:0:0:0:0:1','2022-06-02 11:57:11'),(309,'3adec65e-1609-4e45-b9c0-dfe88de3340e','admin','/api/menus/tree','0:0:0:0:0:0:0:1','2022-06-02 11:57:11'),(310,'c8222422-d795-4b4f-b844-da7d17161979','admin','/api/account','0:0:0:0:0:0:0:1','2022-06-02 11:57:11'),(311,'4f8e9e15-2665-49a4-8670-bcbef1e56de4','admin','/api/account','0:0:0:0:0:0:0:1','2022-06-02 11:57:12'),(312,'d6081f0a-5262-48e8-abff-2127e805fa15','admin','/api/menus','0:0:0:0:0:0:0:1','2022-06-02 11:57:16'),(313,'a558522d-7b89-4470-b78a-6b58a0ad14be','admin','/api/account','0:0:0:0:0:0:0:1','2022-06-02 11:57:52'),(314,'587a15de-9c12-44c0-b23d-9e985227182f','admin','/api/menus/tree','0:0:0:0:0:0:0:1','2022-06-02 11:57:52'),(315,'c636dadf-81ec-45a0-b0b1-ee91ccf3f5f5','admin','/api/account','0:0:0:0:0:0:0:1','2022-06-02 11:57:52'),(316,'d10ace91-479d-4df4-8330-d42218ac3c1c','admin','/api/account','0:0:0:0:0:0:0:1','2022-06-02 11:57:52'),(317,'3df4c7e3-a75a-43e3-8985-ce44108f659f','admin','/api/menus','0:0:0:0:0:0:0:1','2022-06-02 11:57:53'),(318,'9bae8b5c-3dd0-43df-bf21-d16c0c2abfd1','admin','/api/account','0:0:0:0:0:0:0:1','2022-06-02 11:57:54'),(319,'143a449e-fc4d-42aa-ad56-d35555ec7a6d','admin','/api/account','0:0:0:0:0:0:0:1','2022-06-02 11:57:54'),(320,'8844e2b4-24c5-4e21-a1c6-a0984ebc29f0','admin','/api/account','0:0:0:0:0:0:0:1','2022-06-02 11:57:54'),(321,'90d2ee93-cefd-47c7-b6f4-17b86d4a4f14','admin','/api/menus/tree','0:0:0:0:0:0:0:1','2022-06-02 11:57:54'),(322,'e7106d94-2403-4027-80a3-a32e8e541862','admin','/api/menus','0:0:0:0:0:0:0:1','2022-06-02 11:57:54'),(323,'61b98158-3ad0-448e-844e-3127013fedd8','admin','/api/menus','0:0:0:0:0:0:0:1','2022-06-02 11:58:29'),(324,'df3d6a1e-d53b-4ef7-871e-acb49c47f79d','admin','/api/account','0:0:0:0:0:0:0:1','2022-06-02 11:58:30'),(325,'2c19a172-cef4-4010-95cd-cea063f5cb80','admin','/api/account','0:0:0:0:0:0:0:1','2022-06-02 11:58:30'),(326,'0e691cc4-e684-47f8-a266-6e4c683bed9b','admin','/api/menus/tree','0:0:0:0:0:0:0:1','2022-06-02 11:58:30'),(327,'416bbfe5-008c-4cd1-a54c-2ac24c6637ca','admin','/api/account','0:0:0:0:0:0:0:1','2022-06-02 11:58:30'),(328,'4bf34639-21d7-4a71-8f2c-ce5b001db5be','admin','/api/menus','0:0:0:0:0:0:0:1','2022-06-02 11:58:31'),(329,'f7d7d76b-2824-48b9-b577-1311bdfc4c3b','admin','/api/menus','0:0:0:0:0:0:0:1','2022-06-02 11:58:31'),(330,'4bc8fe3f-c50a-46de-9597-1728e66f99a8','admin','/api/account','0:0:0:0:0:0:0:1','2022-06-02 11:58:35'),(331,'fa738249-7cf1-4831-8cdd-b7dc1f6aa68d','admin','/api/account','0:0:0:0:0:0:0:1','2022-06-02 11:58:35'),(332,'46df1691-1f30-4038-81d2-4c6383f120d9','admin','/api/menus/tree','0:0:0:0:0:0:0:1','2022-06-02 11:58:35'),(333,'b7fe9ffd-2918-49ed-87cd-82296dad3507','admin','/api/account','0:0:0:0:0:0:0:1','2022-06-02 11:58:36'),(334,'66d5901e-4670-450f-9541-5c2471e7cf41','admin','/api/menus','0:0:0:0:0:0:0:1','2022-06-02 11:58:37'),(335,'ed5eaeb8-c387-4ec6-82b7-0be8a5c3d7d9','admin','/api/menus/tree','0:0:0:0:0:0:0:1','2022-06-02 11:58:38'),(336,'40d9eaf1-5092-417a-a0c9-c2bb7e61b180','admin','/api/account','0:0:0:0:0:0:0:1','2022-06-02 11:58:38'),(337,'de819ca3-03ae-4da5-a505-5f30a9d0c15b','admin','/api/account','0:0:0:0:0:0:0:1','2022-06-02 11:58:38'),(338,'c9dff77b-b218-4d17-b5f9-d5f4acf0f75b','admin','/api/account','0:0:0:0:0:0:0:1','2022-06-02 11:58:39'),(339,'c2216fdc-318b-41dc-ad23-c05aa4256914','admin','/api/menus','0:0:0:0:0:0:0:1','2022-06-02 11:58:43'),(340,'95dae6de-ff1b-4220-994e-657c4b94c2fb','admin','/api/menus','0:0:0:0:0:0:0:1','2022-06-02 11:58:50'),(341,'48e27bfa-7f8f-4910-bbb3-158b2bea8954','admin','/api/menus/tree','0:0:0:0:0:0:0:1','2022-06-02 11:58:54'),(342,'2898ccbb-b19f-4e56-b1c7-fea2f9dd3878','admin','/api/account','0:0:0:0:0:0:0:1','2022-06-02 11:58:54'),(343,'e7cd2aed-e504-4436-b414-e97f8c4a4bec','admin','/api/account','0:0:0:0:0:0:0:1','2022-06-02 11:58:54'),(344,'13b4c5d4-f7f6-44bb-9275-7d330c269df5','admin','/api/menus','0:0:0:0:0:0:0:1','2022-06-02 11:58:56'),(345,'1a1e64f1-d115-4630-a643-13e0118a5863','admin','/api/menus','0:0:0:0:0:0:0:1','2022-06-02 11:58:56'),(346,'6523df7e-ad4f-4a56-b92b-2693af29c1af','admin','/api/menus','0:0:0:0:0:0:0:1','2022-06-02 11:58:56'),(347,'cd479015-c665-4a51-b4bc-b08174e731ed','admin','/api/menus/tree','0:0:0:0:0:0:0:1','2022-06-02 11:58:58'),(348,'39ad2ef7-7e9a-46ab-82db-3e8f905d42ed','admin','/api/account','0:0:0:0:0:0:0:1','2022-06-02 11:58:58'),(349,'8a7e34a6-aebf-40ef-a4c6-dc5d1387ac3b','admin','/api/account','0:0:0:0:0:0:0:1','2022-06-02 11:58:58'),(350,'b4c5edde-ae73-4ae2-a76f-b9f880218e38','admin','/api/account','0:0:0:0:0:0:0:1','2022-06-02 11:58:58'),(351,'7c5f1739-a996-4ff6-a6a8-a13357220dbe','admin','/api/account','0:0:0:0:0:0:0:1','2022-06-02 11:58:59'),(352,'0979b520-3c00-48e0-93d4-bb874e6ada1a','admin','/api/account','0:0:0:0:0:0:0:1','2022-06-02 11:58:59'),(353,'e831efff-e1b7-4699-a4c3-18a33e7bc7dc','admin','/api/account','0:0:0:0:0:0:0:1','2022-06-02 11:58:59'),(354,'95317fde-ec32-4e1c-8d9b-386cb9a7e11c','admin','/api/menus/tree','0:0:0:0:0:0:0:1','2022-06-02 11:58:59'),(355,'1d31353e-29c9-4031-a1a9-538a07b6c737','admin','/api/menus','0:0:0:0:0:0:0:1','2022-06-02 11:58:59'),(356,'534acc81-c235-47f6-8318-f38bb746d740','admin','/api/menus','0:0:0:0:0:0:0:1','2022-06-02 11:59:04'),(357,'8ed094fc-b033-476c-83eb-d44ad1706dfb','admin','/api/menus/tree','0:0:0:0:0:0:0:1','2022-06-02 11:59:06'),(358,'edc92b95-1e04-444e-bae9-b9f07f43b1e6','admin','/api/account','0:0:0:0:0:0:0:1','2022-06-02 11:59:06'),(359,'bf9465d7-3536-4ae1-bb46-ce95016955e1','admin','/api/account','0:0:0:0:0:0:0:1','2022-06-02 11:59:06'),(360,'09741912-75b7-4c77-97b9-875ea72215ef','admin','/api/menus','0:0:0:0:0:0:0:1','2022-06-02 11:59:10'),(361,'4edca57b-6501-41cd-aaad-70d31160e0be','admin','/api/menus','0:0:0:0:0:0:0:1','2022-06-02 11:59:12'),(362,'b29fbc39-a2a8-49d1-8a42-6821bfd2ea80','admin','/api/account','0:0:0:0:0:0:0:1','2022-06-02 11:59:14'),(363,'ed3ff1ec-46cd-43c0-aeaf-e6b0af5c2340','admin','/api/menus/tree','0:0:0:0:0:0:0:1','2022-06-02 11:59:14'),(364,'e12a5ae2-1215-42c6-8519-d68f89e3155e','admin','/api/account','0:0:0:0:0:0:0:1','2022-06-02 11:59:14'),(365,'b88393ef-5b3e-44ed-a3ea-ddfdb620736e','admin','/api/menus','0:0:0:0:0:0:0:1','2022-06-02 11:59:16'),(366,'ea37fc37-6997-4a74-84ad-d772b41f486f','admin','/api/menus','0:0:0:0:0:0:0:1','2022-06-02 11:59:16'),(367,'de5a5721-aa8c-49c4-b442-0563eb20424b','admin','/api/account','0:0:0:0:0:0:0:1','2022-06-02 11:59:17'),(368,'69bda122-f4f4-4bb2-931d-ccb0749047ef','admin','/api/account','0:0:0:0:0:0:0:1','2022-06-02 11:59:17'),(369,'5ac6dc20-982f-4f14-ac93-f3deafd4b53a','admin','/api/menus/tree','0:0:0:0:0:0:0:1','2022-06-02 11:59:17'),(370,'2cc0abcd-3247-4049-9dad-58dc6018d512','admin','/api/account','0:0:0:0:0:0:0:1','2022-06-02 11:59:17'),(371,'3e40d5b4-f2fe-4f42-9356-22afd7996681','admin','/api/menus','0:0:0:0:0:0:0:1','2022-06-02 11:59:18'),(372,'5fec9287-a6a6-45f6-b037-abb07e3ebe9f','admin','/api/menus','0:0:0:0:0:0:0:1','2022-06-02 11:59:20'),(373,'70ab161a-5aec-4315-ba62-01a369c0efac','admin','/api/account','0:0:0:0:0:0:0:1','2022-06-02 11:59:22'),(374,'2403d07f-065b-44cb-82a0-d208659ae2f4','admin','/api/menus/tree','0:0:0:0:0:0:0:1','2022-06-02 11:59:22'),(375,'f02b3ace-f08b-43bf-af03-4eae6e3d3dff','admin','/api/account','0:0:0:0:0:0:0:1','2022-06-02 11:59:22'),(376,'0a317406-7efa-48eb-8634-3bbb42c149b0','admin','/api/menus','0:0:0:0:0:0:0:1','2022-06-02 11:59:25'),(377,'91c34bf0-e5a3-4e0a-9dc4-304a770fd0de','admin','/api/menus','0:0:0:0:0:0:0:1','2022-06-02 12:00:15'),(378,'1d75b409-ae02-4ec5-b43c-5155da257ce9','admin','/api/account','0:0:0:0:0:0:0:1','2022-06-02 12:00:17'),(379,'522745f8-a37f-4d61-aa9b-089238e76722','admin','/api/account','0:0:0:0:0:0:0:1','2022-06-02 12:00:17'),(380,'0a344697-e4a9-4631-8210-8c2eccaa24a9','admin','/api/menus/tree','0:0:0:0:0:0:0:1','2022-06-02 12:00:17'),(381,'05ca3719-f1ca-49db-982b-6949b7e41bdd','admin','/api/account','0:0:0:0:0:0:0:1','2022-06-02 12:00:17'),(382,'9772b4f9-aa89-4432-8d38-d25c1f73815b','admin','/api/menus','0:0:0:0:0:0:0:1','2022-06-02 12:00:17'),(383,'b248b203-faf0-4b63-997f-0de48fbabe11','anonymousUser','/api/authenticate','0:0:0:0:0:0:0:1','2022-06-02 12:15:12'),(384,'25a17027-f48b-4665-b106-b6fea9d63e38','admin','/api/account','0:0:0:0:0:0:0:1','2022-06-02 12:15:13'),(385,'aeb3c819-ecca-4a52-a5bf-804d1273ae02','admin','/api/account','0:0:0:0:0:0:0:1','2022-06-02 12:15:16'),(386,'1c88f725-9c1c-45fb-9841-91150379334e','admin','/api/account','0:0:0:0:0:0:0:1','2022-06-02 12:15:16'),(387,'0d79bbcb-818a-4b77-bfe2-a07b102f2300','admin','/api/menus/tree','0:0:0:0:0:0:0:1','2022-06-02 12:15:16'),(388,'c3eb699e-96ac-4825-80ab-f0dfdb0ad83f','admin','/api/account','0:0:0:0:0:0:0:1','2022-06-02 12:15:16'),(389,'8779eeae-48c1-4388-8389-eda74f1ee396','admin','/api/slow-sql-loggings','0:0:0:0:0:0:0:1','2022-06-02 12:15:19'),(390,'4f825a36-1648-4ce1-9de9-a574d5360484','admin','/api/slow-sql-loggings','0:0:0:0:0:0:0:1','2022-06-02 12:15:25'),(391,'f20fdbb5-9b4c-4d78-898b-d51701a0b55e','admin','/api/slow-sql-loggings','0:0:0:0:0:0:0:1','2022-06-02 12:15:56'),(392,'70dd5c16-b6c3-45aa-9cd6-46ef2db92872','admin','/api/account','0:0:0:0:0:0:0:1','2022-06-02 12:15:57'),(393,'e9e035d2-65af-4358-becb-c296c8381fa3','admin','/api/menus/tree','0:0:0:0:0:0:0:1','2022-06-02 12:15:57'),(394,'9b591a5a-fec9-461e-afcb-c8754cd04a51','admin','/api/account','0:0:0:0:0:0:0:1','2022-06-02 12:15:57'),(395,'10be1557-2ff9-458c-be05-a6c247007af5','admin','/api/account','0:0:0:0:0:0:0:1','2022-06-02 12:15:57'),(396,'0abdbbc5-74e0-4a5d-a60b-3873f73e6d01','admin','/api/slow-sql-loggings','0:0:0:0:0:0:0:1','2022-06-02 12:15:57'),(397,'5e41fb6a-900c-47f1-b5b4-bfe69dac7b8b','admin','/api/slow-sql-loggings','0:0:0:0:0:0:0:1','2022-06-02 12:16:01'),(398,'6e52a5e0-486e-4de2-b824-e134f249b7c3','admin','/api/slow-sql-loggings','0:0:0:0:0:0:0:1','2022-06-02 12:16:02'),(399,'b8bfb746-f312-44c2-b108-a9f7191e32e2','admin','/api/slow-sql-loggings','0:0:0:0:0:0:0:1','2022-06-02 12:16:02'),(400,'6e63423b-9d7e-4b19-870e-8c8b23485437','admin','/api/slow-sql-loggings','0:0:0:0:0:0:0:1','2022-06-02 12:16:03'),(401,'2b9a16b5-2d10-44ef-bb02-b2ee039fff7c','admin','/api/online/users','0:0:0:0:0:0:0:1','2022-06-02 12:16:07'),(402,'7945bc92-459a-4af5-87cb-6b902dab010c','admin','/api/request-loggings','0:0:0:0:0:0:0:1','2022-06-02 12:16:08'),(403,'88d0d166-ab21-44a8-aa39-4d41e6f2a67e','admin','/api/slow-sql-loggings','0:0:0:0:0:0:0:1','2022-06-02 12:17:12'),(404,'2773e0e9-872a-4d01-a6b6-c4d2b3444f03','admin','/api/slow-sql-loggings','0:0:0:0:0:0:0:1','2022-06-02 12:18:28'),(405,'e4153b0c-c618-4877-b998-e5d49ab59ea6','admin','/api/account','0:0:0:0:0:0:0:1','2022-06-02 12:18:28'),(406,'22177e70-43d5-4e42-a454-0baaae6b55b9','admin','/api/account','0:0:0:0:0:0:0:1','2022-06-02 12:18:29'),(407,'7a742be4-13bb-4b21-9bbc-3c7c2e8adfcb','admin','/api/menus/tree','0:0:0:0:0:0:0:1','2022-06-02 12:18:29'),(408,'cce9b302-3676-43b7-bebe-a64f868d002a','admin','/api/account','0:0:0:0:0:0:0:1','2022-06-02 12:18:29'),(409,'49c510a6-3270-4a3e-b785-ad92d967b68a','admin','/api/slow-sql-loggings','0:0:0:0:0:0:0:1','2022-06-02 12:18:29'),(410,'f7cfd7fe-a812-4fb8-a97f-06b5716ffc8d','admin','/api/slow-sql-loggings/55','0:0:0:0:0:0:0:1','2022-06-02 12:19:13'),(411,'d79e1d3d-59dc-488c-93ef-36a198296523','admin','/api/slow-sql-loggings','0:0:0:0:0:0:0:1','2022-06-02 12:19:15'),(412,'9d5e5643-eb18-4fcc-92d9-c1d8af403cbc','admin','/api/online/users','0:0:0:0:0:0:0:1','2022-06-02 12:19:25'),(413,'348acf63-69e8-4c22-a8c6-bd60d9ebe442','admin','/api/request-loggings','0:0:0:0:0:0:0:1','2022-06-02 12:19:31'),(414,'b76f7f11-4cb1-47fc-822c-47887027e60f','admin','/api/slow-sql-loggings','0:0:0:0:0:0:0:1','2022-06-02 12:19:34'),(415,'d5c8f47d-7f0a-4651-a58c-6f233fd34a1d','admin','/api/menus/tree','0:0:0:0:0:0:0:1','2022-06-02 12:20:06'),(416,'14c6dfde-f303-4223-99ec-a1dbfc887666','admin','/api/account','0:0:0:0:0:0:0:1','2022-06-02 12:20:06'),(417,'9ce2cc09-e456-4045-a368-61a2628d663c','admin','/api/account','0:0:0:0:0:0:0:1','2022-06-02 12:20:06'),(418,'af956889-bcf4-43be-a72d-3f13528a9617','admin','/api/account','0:0:0:0:0:0:0:1','2022-06-02 12:20:06'),(419,'a1d0460c-289d-4708-845d-110bf766043f','admin','/api/slow-sql-loggings','0:0:0:0:0:0:0:1','2022-06-02 12:20:07'),(420,'49efa2f5-d173-4b1e-8e59-2ddf1038b80d','admin','/api/account','0:0:0:0:0:0:0:1','2022-06-02 12:20:15'),(421,'e0d5580e-2c9c-4c68-9b32-672563c0f047','admin','/api/account','0:0:0:0:0:0:0:1','2022-06-02 12:20:16'),(422,'a6bf6111-48b6-4043-916e-d6b06f7c22f9','admin','/api/account','0:0:0:0:0:0:0:1','2022-06-02 12:20:15'),(423,'75522095-91b5-4cc0-b799-0efaa302842e','admin','/api/menus/tree','0:0:0:0:0:0:0:1','2022-06-02 12:20:15'),(424,'d1f0ee5c-d026-4733-a474-b5c9e4cb25dd','admin','/api/slow-sql-loggings','0:0:0:0:0:0:0:1','2022-06-02 12:20:16'),(425,'7b874655-707b-4ba1-84f6-cadd03514371','admin','/api/account','0:0:0:0:0:0:0:1','2022-06-02 12:20:20'),(426,'2d5e6314-2706-4126-be2c-ebeaaf278fc4','admin','/api/account','0:0:0:0:0:0:0:1','2022-06-02 12:20:20'),(427,'867fdea0-0f49-432b-af4b-0a226b98d49c','admin','/api/menus/tree','0:0:0:0:0:0:0:1','2022-06-02 12:20:20'),(428,'4dfc9797-31fd-4f70-b5ab-c3ef55437a69','admin','/api/account','0:0:0:0:0:0:0:1','2022-06-02 12:20:20'),(429,'07a6342e-8f19-4e60-9f08-9490a9d2b182','admin','/api/slow-sql-loggings','0:0:0:0:0:0:0:1','2022-06-02 12:20:21'),(430,'1d35dd1c-2921-4305-a5ca-e36918936e7e','admin','/api/account','0:0:0:0:0:0:0:1','2022-06-02 12:20:30'),(431,'2994635a-a6b0-4c5c-a9b9-f6f6ae8216ee','admin','/api/menus/tree','0:0:0:0:0:0:0:1','2022-06-02 12:20:30'),(432,'b95c3a00-6c61-46ec-b55f-5ba27e017950','admin','/api/account','0:0:0:0:0:0:0:1','2022-06-02 12:20:30'),(433,'633a041d-5df7-4af7-985e-535b4e64fb74','admin','/api/account','0:0:0:0:0:0:0:1','2022-06-02 12:20:30'),(434,'86fdabaf-a0fd-4efd-bcfd-67c98432a397','admin','/api/slow-sql-loggings','0:0:0:0:0:0:0:1','2022-06-02 12:20:30'),(435,'8f69e1cd-065f-4776-a8e8-a78765434d69','admin','/api/menus/tree','0:0:0:0:0:0:0:1','2022-06-02 12:20:34'),(436,'05c9f83a-b06b-48a9-88ea-197675cdb9a6','admin','/api/account','0:0:0:0:0:0:0:1','2022-06-02 12:20:34'),(437,'9967b847-57a5-405b-b3e0-3d745af6be14','admin','/api/account','0:0:0:0:0:0:0:1','2022-06-02 12:20:34'),(438,'21ec46ee-ec4e-4602-bacd-ac893df334d5','admin','/api/account','0:0:0:0:0:0:0:1','2022-06-02 12:20:34'),(439,'2b9f2fd0-4874-4719-b7d6-09d8d37c17ff','admin','/api/slow-sql-loggings','0:0:0:0:0:0:0:1','2022-06-02 12:20:34'),(440,'557870f3-e746-4387-a3e6-6abf11c5431f','admin','/api/account','0:0:0:0:0:0:0:1','2022-06-02 12:20:35'),(441,'2b2dbad9-e6e3-4a18-958e-d344c5608528','admin','/api/account','0:0:0:0:0:0:0:1','2022-06-02 12:20:35'),(442,'82bbdd38-f40b-49a3-8888-51ef9d93bcfa','admin','/api/menus/tree','0:0:0:0:0:0:0:1','2022-06-02 12:20:35'),(443,'159ab4c8-34e9-4edb-a6a9-c04a23528cf0','admin','/api/account','0:0:0:0:0:0:0:1','2022-06-02 12:20:35'),(444,'ccab4421-372a-4dc1-a17d-8a20131a3ffb','admin','/api/slow-sql-loggings','0:0:0:0:0:0:0:1','2022-06-02 12:20:36'),(445,'1ef5ea31-d767-4235-bbf1-5d2ee089ddcb','admin','/api/account','0:0:0:0:0:0:0:1','2022-06-02 12:20:43'),(446,'d0adb945-6aa0-434a-8272-5e46305f5b57','admin','/api/account','0:0:0:0:0:0:0:1','2022-06-02 12:20:43'),(447,'8b5fe48b-f39d-4166-a633-3ae3da80bb48','admin','/api/menus/tree','0:0:0:0:0:0:0:1','2022-06-02 12:20:43'),(448,'328dd967-66c0-4626-ad26-3b720df2df56','admin','/api/account','0:0:0:0:0:0:0:1','2022-06-02 12:20:43'),(449,'8379248e-ded8-4774-963c-8ad67bbc5e04','admin','/api/slow-sql-loggings','0:0:0:0:0:0:0:1','2022-06-02 12:20:44'),(450,'051c065b-a32c-4242-b798-ff44cccf2354','admin','/api/slow-sql-loggings','0:0:0:0:0:0:0:1','2022-06-02 12:20:50'),(451,'fa191e6f-058e-4af8-9643-cfcb916b1f60','admin','/api/account','0:0:0:0:0:0:0:1','2022-06-02 12:20:52'),(452,'5922004d-9828-4413-9da9-bc4990e37df2','admin','/api/menus/tree','0:0:0:0:0:0:0:1','2022-06-02 12:20:52'),(453,'7e03b28b-b32a-4ce5-8ab9-942a1099ce1b','admin','/api/account','0:0:0:0:0:0:0:1','2022-06-02 12:20:52'),(454,'6c2ab070-3098-48b2-8f9e-edd270b490bf','admin','/api/account','0:0:0:0:0:0:0:1','2022-06-02 12:20:52'),(455,'803667b4-887f-475e-9884-69b3b6e87a54','admin','/api/slow-sql-loggings','0:0:0:0:0:0:0:1','2022-06-02 12:20:53'),(456,'efbfd0ae-d472-4e25-a6ab-0cac2f5af729','admin','/api/account','0:0:0:0:0:0:0:1','2022-06-02 12:20:55'),(457,'36662736-efed-447e-84df-e27ca699a1e8','admin','/api/menus/tree','0:0:0:0:0:0:0:1','2022-06-02 12:20:55'),(458,'bd02b765-5513-45cd-a302-2748e4e9ddc7','admin','/api/account','0:0:0:0:0:0:0:1','2022-06-02 12:20:55'),(459,'6b72bb60-808d-425d-8724-8c12d7ee63fb','admin','/api/account','0:0:0:0:0:0:0:1','2022-06-02 12:20:55'),(460,'92038fa3-247c-4faa-b830-519b260ad321','admin','/api/account','0:0:0:0:0:0:0:1','2022-06-02 12:20:55'),(461,'cfb0e92d-0ba5-4864-a8fd-8112ca22a2d2','admin','/api/menus/tree','0:0:0:0:0:0:0:1','2022-06-02 12:20:55'),(462,'345a77ea-e019-4f23-83eb-27b23c8feca8','admin','/api/account','0:0:0:0:0:0:0:1','2022-06-02 12:20:55'),(463,'858d879e-ddf9-4635-bfc3-9d3574a09a27','admin','/api/account','0:0:0:0:0:0:0:1','2022-06-02 12:20:55'),(464,'1381239a-4030-432c-9d2c-0db4e52febb1','admin','/api/slow-sql-loggings','0:0:0:0:0:0:0:1','2022-06-02 12:20:56'),(465,'8aa72e5a-759d-4803-8920-4cd70325fe25','admin','/api/slow-sql-loggings','0:0:0:0:0:0:0:1','2022-06-02 12:21:16'),(466,'39e65941-6c36-41d0-95be-c1917a86735c','admin','/api/menus/tree','0:0:0:0:0:0:0:1','2022-06-02 12:21:17'),(467,'6c7e1c76-e864-4cd8-8e6a-3d8bdedf9223','admin','/api/account','0:0:0:0:0:0:0:1','2022-06-02 12:21:17'),(468,'616cc8ac-3d34-4025-b1b9-d45b727d13d9','admin','/api/account','0:0:0:0:0:0:0:1','2022-06-02 12:21:17'),(469,'1f1d1482-4e1a-4c9f-a393-e59b7a0ef82e','admin','/api/account','0:0:0:0:0:0:0:1','2022-06-02 12:21:17'),(470,'e506ec73-0552-496f-969b-eff7ce5d73bc','admin','/api/slow-sql-loggings','0:0:0:0:0:0:0:1','2022-06-02 12:21:18'),(471,'a3bbd82c-4db4-4980-8e8b-fdb466804887','admin','/api/account','0:0:0:0:0:0:0:1','2022-06-02 12:21:23'),(472,'06be297a-e526-405d-b40c-83ddcf8e3ae8','admin','/api/account','0:0:0:0:0:0:0:1','2022-06-02 12:21:23'),(473,'8c3d2d89-6457-451d-91ea-f39fb082d82a','admin','/api/menus/tree','0:0:0:0:0:0:0:1','2022-06-02 12:21:23'),(474,'f6f92031-87a9-47eb-ad20-4a7a73f242fa','admin','/api/account','0:0:0:0:0:0:0:1','2022-06-02 12:21:23'),(475,'f826829b-0006-4922-a4d9-7931f3ec9f59','admin','/api/account','0:0:0:0:0:0:0:1','2022-06-02 12:21:23'),(476,'98cbe6de-473a-467d-8b21-55629b4701f6','admin','/api/account','0:0:0:0:0:0:0:1','2022-06-02 12:21:23'),(477,'ac8b61e1-8004-4655-90bb-422dfc2e123a','admin','/api/menus/tree','0:0:0:0:0:0:0:1','2022-06-02 12:21:23'),(478,'ec0b62ce-b415-4680-be2d-b13ba9cea0a2','admin','/api/account','0:0:0:0:0:0:0:1','2022-06-02 12:21:23'),(479,'3a6894a5-c10c-4d43-a4f5-f1807c9def16','admin','/api/slow-sql-loggings','0:0:0:0:0:0:0:1','2022-06-02 12:21:24'),(480,'7ddd4988-8ced-42b3-8311-42ee36bb97d7','admin','/api/menus/tree','0:0:0:0:0:0:0:1','2022-06-02 12:21:27'),(481,'fb9a6890-27fd-486c-a917-625f87f769be','admin','/api/account','0:0:0:0:0:0:0:1','2022-06-02 12:21:27'),(482,'cbd16316-49c6-4f9d-b873-1a981b088f05','admin','/api/account','0:0:0:0:0:0:0:1','2022-06-02 12:21:27'),(483,'e5afb3df-8fb2-4768-8fe3-d5e2a5b6d51f','admin','/api/account','0:0:0:0:0:0:0:1','2022-06-02 12:21:27'),(484,'001055c1-ff33-419b-b574-d4613e21cce6','admin','/api/slow-sql-loggings','0:0:0:0:0:0:0:1','2022-06-02 12:21:27'),(485,'77ccb711-1111-45ef-b14a-59f78e19ac08','admin','/api/slow-sql-loggings','0:0:0:0:0:0:0:1','2022-06-02 12:21:29'),(486,'9046628a-355f-43ad-b29a-6118c3b32007','admin','/api/account','0:0:0:0:0:0:0:1','2022-06-02 12:21:30'),(487,'8a2f288d-2d1c-44ce-9a39-12fbd8a56514','admin','/api/account','0:0:0:0:0:0:0:1','2022-06-02 12:21:30'),(488,'d9df0e9b-d66e-421b-a647-88dab5842205','admin','/api/menus/tree','0:0:0:0:0:0:0:1','2022-06-02 12:21:30'),(489,'71f5c6c6-e78c-4f86-b3e9-d6133328f0db','admin','/api/account','0:0:0:0:0:0:0:1','2022-06-02 12:21:30'),(490,'76490635-58de-48bd-9230-64120e76f62e','admin','/api/slow-sql-loggings','0:0:0:0:0:0:0:1','2022-06-02 12:21:31'),(491,'a253045f-ca06-4fa0-8107-61f4b660c376','admin','/api/slow-sql-loggings','0:0:0:0:0:0:0:1','2022-06-02 12:21:31'),(492,'e1d80a87-9ede-4afb-a58e-9d696f08a1bc','admin','/api/account','0:0:0:0:0:0:0:1','2022-06-02 12:21:33'),(493,'20573c1b-3684-4904-af04-6affded3f76b','admin','/api/menus/tree','0:0:0:0:0:0:0:1','2022-06-02 12:21:33'),(494,'33b0681d-2910-4dfa-8d58-39616c11d8bc','admin','/api/account','0:0:0:0:0:0:0:1','2022-06-02 12:21:33'),(495,'78244aee-26ad-4104-ae68-0bc50ff85b01','admin','/api/account','0:0:0:0:0:0:0:1','2022-06-02 12:21:33'),(496,'8f921d24-d6c9-4a7f-86d0-f6daa03cccf1','admin','/api/slow-sql-loggings','0:0:0:0:0:0:0:1','2022-06-02 12:21:33'),(497,'abdd1f63-2f3e-4c83-ab2d-433e92405ce8','admin','/api/slow-sql-loggings','0:0:0:0:0:0:0:1','2022-06-02 12:21:36'),(498,'77bc314a-cd89-49b9-a90a-a6bb58afa767','admin','/api/account','0:0:0:0:0:0:0:1','2022-06-02 12:21:37'),(499,'40e2dc98-ce44-4e9e-b371-f7bbdf8be6ac','admin','/api/menus/tree','0:0:0:0:0:0:0:1','2022-06-02 12:21:37'),(500,'5a282779-75a4-482f-ac9c-d730f5e1b047','admin','/api/account','0:0:0:0:0:0:0:1','2022-06-02 12:21:37'),(501,'1c9f7023-9132-42fe-b7d7-1ae8f11da396','admin','/api/account','0:0:0:0:0:0:0:1','2022-06-02 12:21:37'),(502,'94eb6b3d-c8fa-44e4-bc52-432dd61beb3c','admin','/api/slow-sql-loggings','0:0:0:0:0:0:0:1','2022-06-02 12:21:37'),(503,'af26d95d-04a4-4782-83de-e9833d5f2500','admin','/api/request-loggings','0:0:0:0:0:0:0:1','2022-06-02 12:21:47'),(504,'6a40cefe-f2da-4511-a136-3f65c2b3b43e','admin','/api/slow-sql-loggings','0:0:0:0:0:0:0:1','2022-06-02 12:21:47'),(505,'919db0da-2a16-4fed-abdd-3655885d56b6','admin','/api/request-loggings','0:0:0:0:0:0:0:1','2022-06-02 12:21:49'),(506,'4c6c1e9b-d82e-4a34-b2ea-331d0bf527e3','admin','/api/menus/tree','0:0:0:0:0:0:0:1','2022-06-02 12:25:57'),(507,'ab4a77a2-1fad-4f22-afbe-fffe8bbb85ea','admin','/api/account','0:0:0:0:0:0:0:1','2022-06-02 12:25:57'),(508,'de6cd643-c7c3-482d-8472-fd95bbd5193c','admin','/api/account','0:0:0:0:0:0:0:1','2022-06-02 12:25:57'),(509,'975a67d4-17f7-43be-b5c0-2cb4eecd63c3','admin','/api/account','0:0:0:0:0:0:0:1','2022-06-02 12:25:57'),(510,'b5d59782-331c-40bb-b143-9486be6619b6','admin','/api/request-loggings','0:0:0:0:0:0:0:1','2022-06-02 12:25:58'),(511,'6b154502-0d63-48d1-93bc-48c5aa02588f','admin','/api/request-loggings','0:0:0:0:0:0:0:1','2022-06-02 12:26:05'),(512,'80391bb4-a2ee-4386-a934-d4507ead0627','admin','/api/request-loggings','0:0:0:0:0:0:0:1','2022-06-02 12:26:13'),(513,'6cfa977e-d44c-44ad-9447-0183ca6c1e45','admin','/api/request-loggings','0:0:0:0:0:0:0:1','2022-06-02 12:26:20'),(514,'b52156e3-ff2d-44a7-a41c-3923bffb403d','admin','/api/request-loggings','0:0:0:0:0:0:0:1','2022-06-02 12:26:23'),(515,'9fc19e96-59a7-468e-bfb0-48dd108ea204','admin','/api/slow-sql-loggings','0:0:0:0:0:0:0:1','2022-06-02 12:26:48'),(516,'6f32fd20-f06c-4021-9483-5e63623af3d5','admin','/api/slow-sql-loggings','0:0:0:0:0:0:0:1','2022-06-02 12:26:50'),(517,'e4bd576a-cd75-4541-b65b-a4b3303706ce','admin','/api/slow-sql-loggings','0:0:0:0:0:0:0:1','2022-06-02 12:26:55'),(518,'7da35e2a-f258-4a43-a6c1-de440c8e79ba','admin','/api/slow-sql-loggings','0:0:0:0:0:0:0:1','2022-06-02 12:26:58'),(519,'c7e7a346-9f69-4bda-a1c7-dd78fcde98b8','admin','/api/request-loggings','0:0:0:0:0:0:0:1','2022-06-02 12:27:09'),(520,'48b79efe-073b-43f7-bd20-2f87b8b63e8a','admin','/api/request-loggings','0:0:0:0:0:0:0:1','2022-06-02 12:27:12'),(521,'fb0d5979-1e7c-4e05-a2a4-1162e80df6ed','admin','/api/online/users','0:0:0:0:0:0:0:1','2022-06-02 12:27:20'),(522,'97a309df-7997-4c83-8a4b-2d6f7ecc0169','admin','/api/request-loggings','0:0:0:0:0:0:0:1','2022-06-02 12:27:29'),(523,'1478fa3c-493c-43b3-8133-971ea332bea5','admin','/api/online/users','0:0:0:0:0:0:0:1','2022-06-02 12:27:30'),(524,'4efe5a56-4579-4d24-8d40-0e17103f9234','admin','/api/slow-sql-loggings','0:0:0:0:0:0:0:1','2022-06-02 12:27:31'),(525,'61ad487f-59a0-47f4-a287-64ca0fc25d9e','admin','/api/request-loggings','0:0:0:0:0:0:0:1','2022-06-02 12:27:32'),(526,'27843b96-78b4-422e-aff1-dd69bf909a5e','admin','/api/slow-sql-loggings','0:0:0:0:0:0:0:1','2022-06-02 12:27:41'),(527,'1ef8d70a-255a-4460-bfe4-f01c68156b09','admin','/api/account','0:0:0:0:0:0:0:1','2022-06-02 12:28:41'),(528,'8a4eacfa-2cde-4486-85d3-11ac9a49f002','admin','/api/account','0:0:0:0:0:0:0:1','2022-06-02 12:28:41'),(529,'7843a7c5-a4b5-4638-ba2d-df753957a388','admin','/api/account','0:0:0:0:0:0:0:1','2022-06-02 12:28:41'),(530,'1199c679-bbe4-4868-b995-e4112c5b71a3','admin','/api/menus/tree','0:0:0:0:0:0:0:1','2022-06-02 12:28:41'),(531,'7e13b9f4-b5de-47d3-a1ae-2d2907bd2d09','admin','/api/account','0:0:0:0:0:0:0:1','2022-06-02 12:28:42'),(532,'a07f7ab9-af2a-4ba9-bf10-6c34f82b0be5','admin','/api/account','0:0:0:0:0:0:0:1','2022-06-02 12:28:42'),(533,'9d1f26af-9b9f-4e42-bd47-a48839c7e28d','admin','/api/menus/tree','0:0:0:0:0:0:0:1','2022-06-02 12:28:42'),(534,'963dd270-8df1-4aea-81c1-f2c3d1a84cf0','admin','/api/account','0:0:0:0:0:0:0:1','2022-06-02 12:28:42'),(535,'bc39dc07-f08a-4512-a797-8bbaaf19ca9b','admin','/api/slow-sql-loggings','0:0:0:0:0:0:0:1','2022-06-02 12:28:42'),(536,'715c6b61-958c-4f5d-a571-54ec06540693','admin','/api/data-permissions','0:0:0:0:0:0:0:1','2022-06-02 12:30:08'),(537,'5455b752-12df-4c11-9725-4265c808ae7d','admin','/api/roles/tree','0:0:0:0:0:0:0:1','2022-06-02 12:30:10'),(538,'7f91163a-e2fd-4f12-95b9-1b6598283d50','admin','/api/data-permissions/tree','0:0:0:0:0:0:0:1','2022-06-02 12:30:10'),(539,'44853cb4-cdc4-4782-bfba-43481b7282a1','admin','/api/menus/tree','0:0:0:0:0:0:0:1','2022-06-02 12:30:10'),(540,'08831605-f8cd-44ac-bedc-0910d81b08f1','admin','/api/roles/tree','0:0:0:0:0:0:0:1','2022-06-02 12:30:10'),(541,'d12f91a5-ee46-46fd-959c-134a4a6805f2','admin','/api/menus/tree','0:0:0:0:0:0:0:1','2022-06-02 12:30:10'),(542,'5715c3b4-3825-447a-87e8-45ed7b6e546f','admin','/api/menus/tree','0:0:0:0:0:0:0:1','2022-06-02 12:30:11'),(543,'d35b6ba1-85c6-4815-9b6e-43642a1f94f2','admin','/api/roles/tree','0:0:0:0:0:0:0:1','2022-06-02 12:30:11'),(544,'9d27a042-515a-4445-bf48-87e2b0e60144','admin','/api/online/users','0:0:0:0:0:0:0:1','2022-06-02 12:30:16'),(545,'5a110628-9398-48c0-bad1-a8d39881a2a6','admin','/api/request-loggings','0:0:0:0:0:0:0:1','2022-06-02 12:30:17'),(546,'f20cee31-06d2-4631-8740-529aba96b6d3','admin','/api/slow-sql-loggings','0:0:0:0:0:0:0:1','2022-06-02 12:30:18'),(547,'94311f98-17d4-4faa-bcbb-4b6c468b820c','admin','/api/account','0:0:0:0:0:0:0:1','2022-06-02 13:47:32'),(548,'74261f22-c993-4c55-9193-294a322b01ce','admin','/api/account','0:0:0:0:0:0:0:1','2022-06-02 13:47:32'),(549,'fa01cff2-0d91-4d9d-888b-5a78d25d83ff','admin','/api/menus/tree','0:0:0:0:0:0:0:1','2022-06-02 13:47:32'),(550,'9db4a1be-7cdc-4458-b4fa-500ed5f71fa7','admin','/api/account','0:0:0:0:0:0:0:1','2022-06-02 13:47:32'),(551,'8d2d6ec9-67fc-4ffd-8713-8ada073a31c8','admin','/api/slow-sql-loggings','0:0:0:0:0:0:0:1','2022-06-02 13:47:33'),(552,'65541301-d848-44f5-a708-a5ba869659c2','admin','/api/request-loggings','0:0:0:0:0:0:0:1','2022-06-02 13:47:39'),(553,'4377affe-7477-42cc-8a93-cf678ace3491','admin','/api/slow-sql-loggings','0:0:0:0:0:0:0:1','2022-06-02 13:47:44'),(554,'004a0432-2fe4-489c-afa1-eaf8edfda35b','admin','/api/menus/tree','0:0:0:0:0:0:0:1','2022-06-02 13:49:07'),(555,'f3968868-2412-44bd-b9b0-4c388fa28b60','admin','/api/roles/tree','0:0:0:0:0:0:0:1','2022-06-02 13:49:07'),(556,'8b043098-4c8f-4e37-9c32-1754986a26b8','admin','/api/request-loggings','0:0:0:0:0:0:0:1','2022-06-02 13:49:15'),(557,'29f12454-268c-449a-ae1d-c1dc94f336d6','admin','/api/slow-sql-loggings','0:0:0:0:0:0:0:1','2022-06-02 13:49:20'),(558,'7a90e7e1-e834-430e-a73e-844a51122d19','admin','/api/data-permissions','0:0:0:0:0:0:0:1','2022-06-02 14:13:44'),(559,'92f19a75-9d54-46aa-a2f3-ab1f34a5d2e0','admin','/api/roles/tree','0:0:0:0:0:0:0:1','2022-06-02 14:13:45'),(560,'201d4e9d-5f2b-4027-9420-11d7db9fd133','admin','/api/data-permissions/tree','0:0:0:0:0:0:0:1','2022-06-02 14:13:45'),(561,'5378a976-3189-407a-9088-f6376ef44f8c','admin','/api/menus/tree','0:0:0:0:0:0:0:1','2022-06-02 14:13:45'),(562,'326bdb7b-e5fd-452e-99cb-b7d01388f866','admin','/api/menus/tree','0:0:0:0:0:0:0:1','2022-06-02 14:13:46'),(563,'69e33766-2ba4-437c-9054-5729dd1914aa','admin','/api/roles/tree','0:0:0:0:0:0:0:1','2022-06-02 14:13:46'),(564,'d647605e-79f5-4f03-8269-2cd500cf7bd5','admin','/api/leave-types','0:0:0:0:0:0:0:1','2022-06-02 14:13:47'),(565,'1b4cdfde-0101-4dab-a9c1-1d043ab11835','admin','/api/ui-components','0:0:0:0:0:0:0:1','2022-06-02 14:13:48'),(566,'2210b592-601f-441d-bd9f-1300e202cdcd','admin','/api/menus','0:0:0:0:0:0:0:1','2022-06-02 14:13:49'),(567,'c21f9866-ac1d-4bb2-b82e-db54490666c2','admin','/api/ui-tool-buttons','0:0:0:0:0:0:0:1','2022-06-02 14:13:49'),(568,'f8bdf310-ddd5-4351-8b01-39fd97acb3cf','admin','/api/ui-editforms','0:0:0:0:0:0:0:1','2022-06-02 14:13:49'),(569,'943a0614-f4d7-4d20-8286-6c9ac272de21','admin','/api/online/users','0:0:0:0:0:0:0:1','2022-06-02 14:21:20'),(570,'b3aa90c5-736f-44ed-99bf-6dbbfe87c8e2','admin','/api/request-loggings','0:0:0:0:0:0:0:1','2022-06-02 14:21:21'),(571,'af6fa852-f002-4c21-9904-f779e847fba1','admin','/api/slow-sql-loggings','0:0:0:0:0:0:0:1','2022-06-02 14:21:23'),(572,'8a328fb9-a0d9-415d-b98e-646f0b9fac75','admin','/api/request-loggings','0:0:0:0:0:0:0:1','2022-06-02 14:21:25'),(573,'d765dccc-74a2-479e-ae30-e315c36d6b1f','admin','/api/request-loggings','0:0:0:0:0:0:0:1','2022-06-02 14:28:00'),(574,'5daebed0-f3d2-4196-a52d-59c342a74930','admin','/api/request-loggings','0:0:0:0:0:0:0:1','2022-06-02 14:28:01'),(575,'88e01d37-3ec4-4f2b-80ff-80e304ef0035','admin','/api/request-loggings','0:0:0:0:0:0:0:1','2022-06-02 14:28:01'),(576,'8fddc1cc-5152-4f26-a62c-b1476653455d','admin','/api/request-loggings','0:0:0:0:0:0:0:1','2022-06-02 14:28:02'),(577,'5fcbb37c-f9f4-44b7-818c-1f8e10f70c24','admin','/api/request-loggings','0:0:0:0:0:0:0:1','2022-06-02 14:28:04'),(578,'47d0140d-c1b6-4a57-b5a5-1f0125d2b218','admin','/api/request-loggings','0:0:0:0:0:0:0:1','2022-06-02 14:28:05'),(579,'375e9359-fbbf-40d1-801c-09a504e1a7ca','admin','/api/request-loggings','0:0:0:0:0:0:0:1','2022-06-02 14:28:06'),(580,'9b68e2a5-1d46-4e63-b203-62e296b89177','admin','/api/slow-sql-loggings','0:0:0:0:0:0:0:1','2022-06-02 14:28:28'),(581,'1463eb6d-cc34-4668-ac14-75e4fd4da382','admin','/api/online/users','0:0:0:0:0:0:0:1','2022-06-02 14:28:44'),(582,'cc2e0ba5-1f3d-4109-aff0-6515dfc0d6bd','admin','/api/request-loggings','0:0:0:0:0:0:0:1','2022-06-02 14:28:45'),(583,'020cd22b-e772-4fa4-9fc0-af331ca48560','admin','/api/slow-sql-loggings','0:0:0:0:0:0:0:1','2022-06-02 14:28:47'),(584,'c27d5085-300a-45b4-bdb8-f697ee6e65fd','anonymousUser','/api/authenticate','0:0:0:0:0:0:0:1','2022-06-02 15:20:06'),(585,'63fc85d0-7426-4fbc-bc45-5e115262d948','admin','/api/account','0:0:0:0:0:0:0:1','2022-06-02 15:20:08'),(586,'651cae3b-227b-4c48-8f8a-ece62da35a08','admin','/api/menus/tree','0:0:0:0:0:0:0:1','2022-06-02 15:20:12'),(587,'b6a84e3e-4c03-44ba-94d0-242a04cbb47d','admin','/api/account','0:0:0:0:0:0:0:1','2022-06-02 15:20:12'),(588,'3b4546c4-db7b-46b1-bcc5-13fd4e7c7b06','admin','/api/account','0:0:0:0:0:0:0:1','2022-06-02 15:20:12'),(589,'d44a25e0-633c-4090-9d9f-af9b7a45c478','admin','/api/account','0:0:0:0:0:0:0:1','2022-06-02 15:20:13'),(590,'a62220e1-cd51-49da-82ad-fe378af1b1dc','admin','/api/task-params','0:0:0:0:0:0:0:1','2022-06-02 15:20:22'),(591,'c8460992-2c8c-44a4-8fce-e6d6a94c41e6','admin','/api/task-params/1','0:0:0:0:0:0:0:1','2022-06-02 15:20:26'),(592,'6e467627-5167-4956-aa1d-86b13cfc3630','admin','/api/task-params','0:0:0:0:0:0:0:1','2022-06-02 15:20:27'),(593,'e791cc47-acfa-4a18-a751-d971639a53fd','admin','/api/task-params/2','0:0:0:0:0:0:0:1','2022-06-02 15:20:29'),(594,'31dff139-ddea-4d34-8751-94621d6576a1','admin','/api/task-params','0:0:0:0:0:0:0:1','2022-06-02 15:20:30'),(595,'8c2f8617-5401-4f97-852d-5decd0466ca7','admin','/api/task-params/3','0:0:0:0:0:0:0:1','2022-06-02 15:20:41'),(596,'d27ead25-32e5-49af-9003-4d13699066e7','admin','/api/task-params','0:0:0:0:0:0:0:1','2022-06-02 15:20:42'),(597,'1bb2f641-3f3c-42c9-a16b-f61a1c213bb3','admin','/api/task-params/4','0:0:0:0:0:0:0:1','2022-06-02 15:20:43'),(598,'0652a4ee-daf9-47d1-ad9f-1c2e8e97b0e1','admin','/api/task-params','0:0:0:0:0:0:0:1','2022-06-02 15:20:44'),(599,'8c2082e5-5966-4a93-9c3b-e05b6ec8c00e','admin','/api/task-params/5','0:0:0:0:0:0:0:1','2022-06-02 15:20:46'),(600,'1d41bed6-bddd-4d20-8881-b0cde20b0f7d','admin','/api/task-params','0:0:0:0:0:0:0:1','2022-06-02 15:20:46'),(601,'d72eeead-8846-43f4-bd8e-80d52e2c71e8','admin','/api/task-params/6','0:0:0:0:0:0:0:1','2022-06-02 15:20:48'),(602,'df45d7a9-1f02-4e00-8abb-34e56ee24326','admin','/api/task-params','0:0:0:0:0:0:0:1','2022-06-02 15:20:49'),(603,'a1868268-4d22-43aa-8b2b-58dbb7c91aa8','admin','/api/task-params/7','0:0:0:0:0:0:0:1','2022-06-02 15:20:50'),(604,'88e5fed8-b588-4e8a-8399-1a4c8dd91457','admin','/api/task-params','0:0:0:0:0:0:0:1','2022-06-02 15:20:51'),(605,'6bf86038-bb8b-4a00-9b2c-8e8b2e330aca','admin','/api/task-params/8','0:0:0:0:0:0:0:1','2022-06-02 15:20:52'),(606,'ee534f64-6dff-41ea-ae48-757aff16feac','admin','/api/task-params','0:0:0:0:0:0:0:1','2022-06-02 15:20:53'),(607,'733f26a5-bfdc-4a2a-8550-adf2dd4878aa','admin','/api/task-params/9','0:0:0:0:0:0:0:1','2022-06-02 15:20:55'),(608,'fe3aec28-bb1a-4aa8-b9ba-acd92917e623','admin','/api/task-params','0:0:0:0:0:0:0:1','2022-06-02 15:20:55'),(609,'551ac44f-2952-40af-8cb7-83ccefbe8610','admin','/api/task-params/10','0:0:0:0:0:0:0:1','2022-06-02 15:20:57'),(610,'47035e1d-ac3f-4023-8e01-64dbf3b12633','admin','/api/task-params','0:0:0:0:0:0:0:1','2022-06-02 15:20:57'),(611,'e145a8b0-9636-4ce2-95c6-dbf9c6286832','admin','/api/task-params','0:0:0:0:0:0:0:1','2022-06-02 15:21:04'),(612,'7f8288ed-b85f-4dbb-87c3-6af27fc53835','admin','/api/system-params','0:0:0:0:0:0:0:1','2022-06-02 15:21:07'),(613,'15dadf6b-968a-4c05-87d9-4470c4a31738','admin','/api/system-params/1','0:0:0:0:0:0:0:1','2022-06-02 15:21:11'),(614,'2126a73d-83c1-4538-9bea-d91cfe0605c0','admin','/api/system-params','0:0:0:0:0:0:0:1','2022-06-02 15:21:12'),(615,'4d70d1d7-015a-4c33-b054-ca64896534e1','admin','/api/system-params/2','0:0:0:0:0:0:0:1','2022-06-02 15:21:14'),(616,'a70d5a81-3147-4074-8efa-88e4fb2c9a3d','admin','/api/system-params','0:0:0:0:0:0:0:1','2022-06-02 15:21:14'),(617,'8edf9f3c-65d8-4a9f-b3c8-351b2fc79000','admin','/api/system-params/3','0:0:0:0:0:0:0:1','2022-06-02 15:21:16'),(618,'f81207d9-27bd-4c15-a92d-b465fc52b126','admin','/api/system-params','0:0:0:0:0:0:0:1','2022-06-02 15:21:17'),(619,'0c85e3a5-c557-4cbb-a1b5-5ec3956ee22b','admin','/api/system-params/4','0:0:0:0:0:0:0:1','2022-06-02 15:21:19'),(620,'94f02749-1ccf-4aea-bd0f-6322a9391b58','admin','/api/system-params','0:0:0:0:0:0:0:1','2022-06-02 15:21:19'),(621,'7a16194c-8bbe-4be9-9da2-d679d8b531ff','admin','/api/system-params/5','0:0:0:0:0:0:0:1','2022-06-02 15:21:21'),(622,'1cadb462-aa7a-4297-9a85-3d24a6496865','admin','/api/system-params','0:0:0:0:0:0:0:1','2022-06-02 15:21:21'),(623,'42d70125-f930-41b6-9b69-baf7a8ef5b82','admin','/api/system-params/6','0:0:0:0:0:0:0:1','2022-06-02 15:21:23'),(624,'42551d46-da46-4878-a17e-ae61194e6420','admin','/api/system-params','0:0:0:0:0:0:0:1','2022-06-02 15:21:23'),(625,'b76722ab-01f1-469a-ad46-d0a845f50db7','admin','/api/system-params/7','0:0:0:0:0:0:0:1','2022-06-02 15:21:25'),(626,'2c7889ec-0ecb-4bc1-8193-e21318529a05','admin','/api/system-params','0:0:0:0:0:0:0:1','2022-06-02 15:21:26'),(627,'697df8f8-c6d4-4cad-8203-437f09e6be4e','admin','/api/system-params/8','0:0:0:0:0:0:0:1','2022-06-02 15:21:28'),(628,'7258b7ec-e4ad-4928-b9b7-fcc6e2d54990','admin','/api/system-params','0:0:0:0:0:0:0:1','2022-06-02 15:21:28'),(629,'89da7d65-326c-40be-9f12-523d72c423c1','admin','/api/system-params/9','0:0:0:0:0:0:0:1','2022-06-02 15:21:30'),(630,'a00ccffb-4448-48fd-940b-4b2e90cf4cdb','admin','/api/system-params','0:0:0:0:0:0:0:1','2022-06-02 15:21:30'),(631,'154c6545-2e34-4fe1-bf31-3f1fb939c890','admin','/api/system-params/10','0:0:0:0:0:0:0:1','2022-06-02 15:21:33'),(632,'1fa27195-ed1f-408a-9191-936d7b183fba','admin','/api/system-params','0:0:0:0:0:0:0:1','2022-06-02 15:21:33'),(633,'a67b8771-f55d-4f24-b57b-0fff0eeffe71','admin','/api/system-params','0:0:0:0:0:0:0:1','2022-06-02 15:21:42'),(634,'ce0b70fe-8bf4-423b-8141-044a332130da','admin','/api/task-params','0:0:0:0:0:0:0:1','2022-06-02 16:45:47'),(635,'e9d3969d-5721-421b-928c-b5b0008c8cbc','admin','/api/task-params','0:0:0:0:0:0:0:1','2022-06-02 16:48:00'),(636,'45a51278-55e8-472b-a851-c26b56eb4a25','admin','/api/task-params','0:0:0:0:0:0:0:1','2022-06-02 16:48:00'),(637,'4d9b97f9-cd01-44b0-8f97-52bfa453dd21','admin','/api/task-params/11','0:0:0:0:0:0:0:1','2022-06-02 16:48:02'),(638,'1db9cb09-d257-44a3-b4e3-c18c2424c950','admin','/api/task-params/11','0:0:0:0:0:0:0:1','2022-06-02 16:48:04'),(639,'4f133e60-ad9b-4b50-ab42-36c612632741','admin','/api/task-params','0:0:0:0:0:0:0:1','2022-06-02 16:48:05'),(640,'97161123-01df-4c7c-9fbd-fe55de1aa110','anonymousUser','/api/authenticate','0:0:0:0:0:0:0:1','2022-06-02 17:32:42'),(641,'b89b45ff-b7fe-4b86-ae40-11ae2005b7a2','admin','/api/account','0:0:0:0:0:0:0:1','2022-06-02 17:32:43'),(642,'f989dfd5-5d6a-4da1-b105-b887b255fabd','admin','/api/account','0:0:0:0:0:0:0:1','2022-06-02 17:32:46'),(643,'ed685a7f-3d2f-4c2a-97bd-4dd587bfbfa2','admin','/api/menus/tree','0:0:0:0:0:0:0:1','2022-06-02 17:32:46'),(644,'2ea2b65f-09c0-4e21-85c2-5a9d1abf1c4c','admin','/api/account','0:0:0:0:0:0:0:1','2022-06-02 17:32:46'),(645,'ba85fc4b-ef7c-4e64-b738-20f0d4f021cd','admin','/api/account','0:0:0:0:0:0:0:1','2022-06-02 17:32:46'),(646,'b2a29d62-f775-4866-9c07-9907b6d472ec','admin','/api/examples/page/1/size/5','0:0:0:0:0:0:0:1','2022-06-02 17:32:51'),(647,'3245b074-d3a9-4b0b-87ad-8ecdbdf0ef2b','admin','/api/ui-components/menu/0000','0:0:0:0:0:0:0:1','2022-06-02 17:32:51'),(648,'d6b6ad48-752b-4a12-ae74-6716a53a24e5','admin','/api/ui-tool-buttons/menu/0000','0:0:0:0:0:0:0:1','2022-06-02 17:32:51'),(649,'9b0e8c4c-1150-4a8f-ba2e-9343b6f94404','admin','/api/ui-editforms/menu/0000','0:0:0:0:0:0:0:1','2022-06-02 17:32:51'),(650,'78acf658-f27b-46f1-9aba-801cb4dcb8fd','admin','/api/ui-tabs/menu/0000','0:0:0:0:0:0:0:1','2022-06-02 17:32:51'),(651,'336b0aad-2ec9-4d98-8f07-ec01809eadb2','admin','/api/ui-tables/menu/0000','0:0:0:0:0:0:0:1','2022-06-02 17:32:51'),(652,'b98132aa-75e3-4490-889b-f3f8a40a8e3f','admin','/api/ui-components/menu/5','0:0:0:0:0:0:0:1','2022-06-02 17:32:54'),(653,'620e2003-44bd-4e1b-9ebf-35d2d4e4ccce','admin','/api/ui-editforms/menu/5','0:0:0:0:0:0:0:1','2022-06-02 17:32:54'),(654,'cf620532-19d4-4cc9-866d-f91ee7039fcb','admin','/api/ui-tool-buttons/menu/5','0:0:0:0:0:0:0:1','2022-06-02 17:32:54'),(655,'d6e446b1-83ac-44a6-a065-79e09d2d8d5b','admin','/api/ui-components/menu/6','0:0:0:0:0:0:0:1','2022-06-02 17:32:54'),(656,'706debad-257b-4a29-a4b9-5bda556937e9','admin','/api/leave-slips','0:0:0:0:0:0:0:1','2022-06-02 17:32:54'),(657,'95078e51-889d-4ac8-a622-0a7ad8597c64','admin','/api/ui-tool-buttons/menu/6','0:0:0:0:0:0:0:1','2022-06-02 17:32:55'),(658,'1c35db30-2726-4303-8006-bdda993a05a4','admin','/api/ui-tables/menu/6','0:0:0:0:0:0:0:1','2022-06-02 17:32:55'),(659,'73cb45c2-f86d-47e7-a2b6-3b1266461650','admin','/api/ui-tool-buttons','0:0:0:0:0:0:0:1','2022-06-02 17:32:57'),(660,'e0f69f56-3375-43a5-ac19-344826bf86f9','admin','/api/ui-components','0:0:0:0:0:0:0:1','2022-06-02 17:32:58'),(661,'999b10eb-ad61-499d-b27f-a627580ca22d','admin','/api/leave-types','0:0:0:0:0:0:0:1','2022-06-02 17:33:00'),(662,'6cbf9905-e318-495f-b1f1-37308743c279','admin','/api/data-permissions','0:0:0:0:0:0:0:1','2022-06-02 17:33:02'),(663,'f746cf9d-b440-42f7-9ad4-804700bff0e7','admin','/api/menus/tree','0:0:0:0:0:0:0:1','2022-06-02 17:33:02'),(664,'aac38e85-f303-4b55-822c-56c6fd7a3615','admin','/api/roles/tree','0:0:0:0:0:0:0:1','2022-06-02 17:33:02'),(665,'a91d5ede-08a7-4f39-a45c-a925efa5ef5d','admin','/api/data-permissions/tree','0:0:0:0:0:0:0:1','2022-06-02 17:33:02'),(666,'18135098-b7f3-499e-8477-153b4a87aea1','admin','/api/slow-sql-loggings','0:0:0:0:0:0:0:1','2022-06-02 17:33:06'),(667,'be42f09d-bfa6-43d0-81df-7e73b32d176a','admin','/api/task-params','0:0:0:0:0:0:0:1','2022-06-02 17:33:10'),(668,'23f69d38-088f-41a5-b74e-7bb9513a6855','admin','/api/system-params','0:0:0:0:0:0:0:1','2022-06-02 17:33:15'),(669,'d741fa53-cc21-4b78-9f68-5d6bdac716ee','admin','/api/task-params','0:0:0:0:0:0:0:1','2022-06-02 17:33:18'),(670,'be9f89e7-5ff3-401c-8f89-367165ad4237','admin','/api/request-loggings','0:0:0:0:0:0:0:1','2022-06-02 17:33:31'),(671,'eac85ab0-fcdf-44af-911b-c3afc2e24c4b','admin','/api/online/users','0:0:0:0:0:0:0:1','2022-06-02 17:33:33'),(672,'69e6511a-d9ad-4c9d-a998-8dd28541247c','admin','/api/request-loggings','0:0:0:0:0:0:0:1','2022-06-02 17:33:35'),(673,'6cfb607b-76f6-49ae-b4ec-b62c97eb1f6d','admin','/api/slow-sql-loggings','0:0:0:0:0:0:0:1','2022-06-02 17:33:38'),(674,'59d69bd0-6c38-46fc-b588-6d0646059383','admin','/api/request-loggings','0:0:0:0:0:0:0:1','2022-06-02 17:33:41'),(675,'a902d68e-dca2-42b4-8cb7-5ae89e4c9949','admin','/api/data-permissions','0:0:0:0:0:0:0:1','2022-06-02 17:34:57'),(676,'bfc14ac1-9750-4c4d-9ebd-aa72981f4553','admin','/api/leave-types','0:0:0:0:0:0:0:1','2022-06-02 17:34:59'),(677,'4b6ac9bc-f26d-4205-b9be-adbd46d3434d','admin','/api/menus','0:0:0:0:0:0:0:1','2022-06-02 17:35:00'),(678,'ae1c8d5f-98f4-4a2b-bfe9-75d876fe88ed','admin','/api/ui-components','0:0:0:0:0:0:0:1','2022-06-02 17:35:00'),(679,'7e84a9b0-93a4-4850-935d-875099c9701a','admin','/api/ui-tables','0:0:0:0:0:0:0:1','2022-06-02 17:35:01'),(680,'238d9636-e4f8-4535-a382-8da57bed23da','admin','/api/ui-queryforms','0:0:0:0:0:0:0:1','2022-06-02 17:35:02'),(681,'5431c467-5154-4228-86f2-05494eff2a20','admin','/api/online/users','0:0:0:0:0:0:0:1','2022-06-02 17:35:06'),(682,'8a2e7d8b-a4fd-49a8-b56a-079efa40896b','admin','/api/request-loggings','0:0:0:0:0:0:0:1','2022-06-02 17:35:07'),(683,'a227fe61-fc63-4106-b5fa-6dbb522690b9','admin','/api/slow-sql-loggings','0:0:0:0:0:0:0:1','2022-06-02 17:35:08'),(684,'1d4e838a-0e6b-4b28-a97e-7e07cf0467c7','admin','/api/slow-sql-loggings','0:0:0:0:0:0:0:1','2022-06-02 17:35:19'),(685,'5c93b494-b34e-4bb8-8c6c-7b6b2f7c3b74','admin','/api/slow-sql-loggings','0:0:0:0:0:0:0:1','2022-06-02 17:35:22'),(686,'5ce6ed4c-0c34-4f87-b692-dd07037b99e7','admin','/api/data-permissions','0:0:0:0:0:0:0:1','2022-06-02 17:35:32'),(687,'11e91d51-b65e-4768-88fc-795732052497','admin','/api/roles/tree','0:0:0:0:0:0:0:1','2022-06-02 17:35:32'),(688,'fccdeb34-1d93-4631-8524-db3301f46087','admin','/api/data-permissions/tree','0:0:0:0:0:0:0:1','2022-06-02 17:35:32'),(689,'2d0cbc03-8c39-4305-8ea7-3fa227402c7a','admin','/api/menus/tree','0:0:0:0:0:0:0:1','2022-06-02 17:35:32'),(690,'5bc73582-1666-4af2-a154-f96b1133b41d','admin','/api/leave-types','0:0:0:0:0:0:0:1','2022-06-02 17:35:35'),(691,'e010cd3a-a705-458d-a475-db55aa4d5ad5','admin','/api/menus','0:0:0:0:0:0:0:1','2022-06-02 17:35:54'),(692,'92dbdb5b-4e0f-4f7f-971e-91e0e75f9e71','admin','/api/menus','0:0:0:0:0:0:0:1','2022-06-02 17:36:03'),(693,'7fe6f2a8-9aa7-43e1-9523-b5f78cd5e31c','admin','/api/menus','0:0:0:0:0:0:0:1','2022-06-02 17:36:10'),(694,'0096a3f1-6eae-4dac-aaeb-e7f1ee1e0ae0','admin','/api/menus/23','0:0:0:0:0:0:0:1','2022-06-02 17:36:15'),(695,'c2128c47-3b98-45e6-a91d-ae08702f3b29','admin','/api/menus/23','0:0:0:0:0:0:0:1','2022-06-02 17:37:09'),(696,'cea5ccc5-4534-456a-9d2f-8625dc6cdd5a','admin','/api/menus','0:0:0:0:0:0:0:1','2022-06-02 17:37:09'),(697,'afe1eb5a-ae1f-47ee-bfab-e8598fda4a71','admin','/api/menus','0:0:0:0:0:0:0:1','2022-06-02 17:37:17'),(698,'03b2e88c-2bd1-4ca3-b563-c0ded84bac3b','admin','/api/menus','0:0:0:0:0:0:0:1','2022-06-02 17:37:19'),(699,'72f19ff7-931f-44f3-9a1d-d55fc11369d9','admin','/api/menus/24','0:0:0:0:0:0:0:1','2022-06-02 17:37:22'),(700,'81e62237-a79f-45bb-abd1-a9167aa031f4','admin','/api/menus/24','0:0:0:0:0:0:0:1','2022-06-02 17:37:31'),(701,'85dcdf26-d4b8-45d4-b614-e646f9c47f26','admin','/api/menus','0:0:0:0:0:0:0:1','2022-06-02 17:37:32'),(702,'1f0fa518-cd69-4ace-81a5-b9bb2a639435','admin','/api/account','0:0:0:0:0:0:0:1','2022-06-02 17:37:35'),(703,'5bd20749-b16d-4685-b1d2-596a67373e9f','admin','/api/menus/tree','0:0:0:0:0:0:0:1','2022-06-02 17:37:35'),(704,'a6896fc3-f26c-4f34-afc8-dfc7200e7d7c','admin','/api/account','0:0:0:0:0:0:0:1','2022-06-02 17:37:35'),(705,'dbdb7d49-09ec-431d-bb90-f3ced2d97351','admin','/api/account','0:0:0:0:0:0:0:1','2022-06-02 17:37:35'),(706,'b867c0eb-ca5d-4bfa-9296-6fa74dafbb38','admin','/api/menus','0:0:0:0:0:0:0:1','2022-06-02 17:37:36'),(707,'ac71d942-d642-4443-af0e-49e4ae1bccef','admin','/api/system-params','0:0:0:0:0:0:0:1','2022-06-02 17:37:38'),(708,'bde2787a-d037-4723-8d3c-b64de6366a70','admin','/api/task-params','0:0:0:0:0:0:0:1','2022-06-02 17:37:40'),(709,'ea2bd6e3-f28c-4392-9596-7c1ef2283322','admin','/api/menus','0:0:0:0:0:0:0:1','2022-06-02 17:37:56'),(710,'4d7e2dc2-d3d3-4eb5-a38a-d04ca20bc624','admin','/api/ui-components','0:0:0:0:0:0:0:1','2022-06-02 17:37:56'),(711,'8918c007-98b1-434e-ab8c-5ec17f1aca76','admin','/api/ui-tables','0:0:0:0:0:0:0:1','2022-06-02 17:37:58'),(712,'c0337003-7ad9-49d2-9d0e-a8341dabbd33','admin','/api/examples/page/1/size/5','0:0:0:0:0:0:0:1','2022-06-02 17:38:00'),(713,'29c92cd5-c0a0-4a85-94fd-b0f9763213fe','admin','/api/ui-components/menu/0000','0:0:0:0:0:0:0:1','2022-06-02 17:38:00'),(714,'81b3f9bd-deeb-4f14-844d-71fe43cef81b','admin','/api/ui-editforms/menu/0000','0:0:0:0:0:0:0:1','2022-06-02 17:38:00'),(715,'7e2f7c4e-6f10-4b00-b11b-ac7a59eca048','admin','/api/ui-tool-buttons/menu/0000','0:0:0:0:0:0:0:1','2022-06-02 17:38:00'),(716,'ed02aaf8-bcdd-4008-a14c-da50e91dcbca','admin','/api/ui-tables/menu/0000','0:0:0:0:0:0:0:1','2022-06-02 17:38:00'),(717,'8934c6d5-7d2f-46c9-ac11-285fbe2f2b7b','admin','/api/ui-tabs/menu/0000','0:0:0:0:0:0:0:1','2022-06-02 17:38:00'),(718,'e7980b0e-04e6-41c1-86d3-95a4559f97fb','admin','/api/ui-components/menu/5','0:0:0:0:0:0:0:1','2022-06-02 17:38:01'),(719,'28fa76ea-7ef1-45dc-9daf-7a25281c0e72','admin','/api/ui-tool-buttons/menu/5','0:0:0:0:0:0:0:1','2022-06-02 17:38:01'),(720,'d3d76507-3f40-4409-baea-f21ea0cf1254','admin','/api/ui-editforms/menu/5','0:0:0:0:0:0:0:1','2022-06-02 17:38:01'),(721,'8e05d64a-fea8-42b0-be30-fa1c0f41db00','admin','/api/ui-components/menu/6','0:0:0:0:0:0:0:1','2022-06-02 17:38:02'),(722,'f33b52ad-2415-4aa4-ac52-2ec10280fbe7','admin','/api/leave-slips','0:0:0:0:0:0:0:1','2022-06-02 17:38:02'),(723,'577c68bd-7f78-4ede-bd5f-fe6602cdb146','admin','/api/ui-tool-buttons/menu/6','0:0:0:0:0:0:0:1','2022-06-02 17:38:02'),(724,'ff8bf946-f7a2-46ad-bfcb-39d4ed1659a5','admin','/api/ui-tables/menu/6','0:0:0:0:0:0:0:1','2022-06-02 17:38:02'),(725,'bf40423d-a048-443b-a026-2572a38d3e07','admin','/api/account','0:0:0:0:0:0:0:1','2022-06-02 17:46:07'),(726,'e03c9653-cfb3-4ddc-8999-9de25388ae8b','admin','/api/account','0:0:0:0:0:0:0:1','2022-06-02 17:46:07'),(727,'bd0f69a4-632e-41e9-adcd-7d29c96cc410','admin','/api/menus/tree','0:0:0:0:0:0:0:1','2022-06-02 17:46:07'),(728,'530cabcc-4773-4d48-ad6d-d84f5bbd81ab','admin','/api/examples/page/1/size/5','0:0:0:0:0:0:0:1','2022-06-02 17:46:11'),(729,'b76c68df-cda2-48f8-851f-d26abefe5ed6','admin','/api/ui-components/menu/0000','0:0:0:0:0:0:0:1','2022-06-02 17:46:11'),(730,'db5324ab-0f3e-463e-abee-33d5f1df44c9','admin','/api/ui-tool-buttons/menu/0000','0:0:0:0:0:0:0:1','2022-06-02 17:46:11'),(731,'9b7051ad-e38b-47bc-8634-2a6dc6ba18a9','admin','/api/ui-tabs/menu/0000','0:0:0:0:0:0:0:1','2022-06-02 17:46:11'),(732,'710a01cc-cd47-4dde-aed0-e46557248557','admin','/api/ui-tables/menu/0000','0:0:0:0:0:0:0:1','2022-06-02 17:46:11'),(733,'588ac8bc-cf0b-4043-b667-e356777f868f','admin','/api/ui-editforms/menu/0000','0:0:0:0:0:0:0:1','2022-06-02 17:46:11'),(734,'1119ef14-e5e0-436f-b576-6b0140e629d7','admin','/api/ui-components/menu/5','0:0:0:0:0:0:0:1','2022-06-02 17:46:12'),(735,'e5619322-4038-4d0a-a4e1-84a8bc140175','admin','/api/ui-editforms/menu/5','0:0:0:0:0:0:0:1','2022-06-02 17:46:12'),(736,'9e492c6b-cc89-4f8d-a22d-dfda06c764e8','admin','/api/ui-tool-buttons/menu/5','0:0:0:0:0:0:0:1','2022-06-02 17:46:12'),(737,'2c6fd46c-c5ab-41d4-93d8-7465bd166301','admin','/api/ui-components','0:0:0:0:0:0:0:1','2022-06-02 17:46:14'),(738,'6ad5c392-3a10-4bc8-ac14-d801fa760f7f','admin','/api/menus','0:0:0:0:0:0:0:1','2022-06-02 17:46:14'),(739,'4fbb4bbd-99c9-48e3-80d3-215923381c4f','admin','/api/ui-tool-buttons','0:0:0:0:0:0:0:1','2022-06-02 17:46:15'),(740,'17003d1e-b585-4d6e-8a16-191539234d6a','admin','/api/ui-tables','0:0:0:0:0:0:0:1','2022-06-02 17:46:16'),(741,'5d5f46c9-ee85-4f65-acec-715048266dd8','admin','/api/ui-editforms','0:0:0:0:0:0:0:1','2022-06-02 17:46:17'),(742,'dbd7b9d3-ab86-4942-8c5e-5fdf23c2054e','admin','/api/ui-queryforms','0:0:0:0:0:0:0:1','2022-06-02 17:46:18'),(743,'91c9b600-b1f4-4156-940c-9bb3a30537e3','admin','/api/ui-tabs','0:0:0:0:0:0:0:1','2022-06-02 17:46:19'),(744,'6fc75b7a-18e6-4842-907f-6c6058bd2cd0','admin','/api/leave-types','0:0:0:0:0:0:0:1','2022-06-02 17:46:22'),(745,'97f59050-cb6b-4618-abe3-a29226d32f85','admin','/api/menus/tree','0:0:0:0:0:0:0:1','2022-06-02 17:46:24'),(746,'2e8b7251-9b0d-4b66-b4b7-5e367c6f83aa','admin','/api/data-permissions/tree','0:0:0:0:0:0:0:1','2022-06-02 17:46:24'),(747,'726c6f43-ad3c-40a9-8b03-1d242bec440a','admin','/api/roles/tree','0:0:0:0:0:0:0:1','2022-06-02 17:46:24'),(748,'4c8f0c7e-3837-4b41-9a82-6044352e8114','admin','/api/data-permissions','0:0:0:0:0:0:0:1','2022-06-02 17:46:25'),(749,'55f10516-43cf-4238-8b4f-352eda7b0f4e','admin','/api/roles/tree','0:0:0:0:0:0:0:1','2022-06-02 17:46:25'),(750,'49cb21df-2972-4be1-aa33-f93c45d46f78','admin','/api/menus/tree','0:0:0:0:0:0:0:1','2022-06-02 17:46:25'),(751,'a6440f93-4fd3-4666-a5a3-aa43cfed06db','admin','/api/system-params','0:0:0:0:0:0:0:1','2022-06-02 17:46:29'),(752,'ee1139ff-dacc-476f-a4b2-3108c3206a8c','admin','/api/task-params','0:0:0:0:0:0:0:1','2022-06-02 17:46:30'),(753,'92de5372-8d0d-447b-9718-cbfc020bcdb3','admin','/api/online/users','0:0:0:0:0:0:0:1','2022-06-02 17:46:32'),(754,'40705576-a9df-489a-bdfb-6a70c4942219','admin','/api/request-loggings','0:0:0:0:0:0:0:1','2022-06-02 17:46:33'),(755,'eee564eb-86fa-49f9-a079-25c1e196f5f5','admin','/api/slow-sql-loggings','0:0:0:0:0:0:0:1','2022-06-02 17:46:35'),(756,'a978407e-cd44-4a40-8d9a-451887ae79df','admin','/api/ui-components/menu/0000','0:0:0:0:0:0:0:1','2022-06-02 17:46:46'),(757,'bf50bb25-d432-413b-82fa-8ef8cd27061e','admin','/api/examples/page/1/size/5','0:0:0:0:0:0:0:1','2022-06-02 17:46:46'),(758,'77312c6c-9b93-4b5c-bea8-141e73fa7d88','admin','/api/ui-tool-buttons/menu/0000','0:0:0:0:0:0:0:1','2022-06-02 17:46:47'),(759,'3d591ff4-c434-4848-90d0-18df6b251d9e','admin','/api/ui-editforms/menu/0000','0:0:0:0:0:0:0:1','2022-06-02 17:46:47'),(760,'3adddffa-3de5-4605-80d4-090ece6d05f2','admin','/api/ui-tabs/menu/0000','0:0:0:0:0:0:0:1','2022-06-02 17:46:47'),(761,'be4f36b9-330d-4c6e-be53-ad372ecf00c8','admin','/api/ui-tables/menu/0000','0:0:0:0:0:0:0:1','2022-06-02 17:46:47'),(762,'326bea6d-c39b-4aee-adab-be3da0701d5d','admin','/api/ui-components/menu/5','0:0:0:0:0:0:0:1','2022-06-02 17:46:48'),(763,'6dac3a51-a2d3-4220-b4ca-202feb51d252','admin','/api/ui-tool-buttons/menu/5','0:0:0:0:0:0:0:1','2022-06-02 17:46:48'),(764,'28ba81fa-da7c-41de-ba56-7b29ca78d56d','admin','/api/ui-editforms/menu/5','0:0:0:0:0:0:0:1','2022-06-02 17:46:48'),(765,'59cb5279-f939-4848-b13a-2d41e695dac0','admin','/api/ui-tables','0:0:0:0:0:0:0:1','2022-06-02 17:46:50'),(766,'6f079c58-2cea-4a47-a25d-ed4786d90351','admin','/api/ui-queryforms','0:0:0:0:0:0:0:1','2022-06-02 17:47:52'),(767,'5c7860a1-03ff-4ba3-bf58-f6af56b71f04','admin','/api/ui-editforms','0:0:0:0:0:0:0:1','2022-06-02 17:47:53'),(768,'823dbf67-40b0-43ed-a11e-177a86f662b9','admin','/api/ui-tabs','0:0:0:0:0:0:0:1','2022-06-02 17:47:54'),(769,'d5cd634b-0777-41cb-a995-d2ae4d4861f8','admin','/api/roles/tree','0:0:0:0:0:0:0:1','2022-06-02 17:47:56'),(770,'94b08b54-7cb1-4cf9-a0a0-f80508cc270b','admin','/api/data-permissions/tree','0:0:0:0:0:0:0:1','2022-06-02 17:47:56'),(771,'e434d9d6-77dd-4b28-9884-eb71b5b83c8d','admin','/api/menus/tree','0:0:0:0:0:0:0:1','2022-06-02 17:47:56'),(772,'de965483-8ad2-4084-b349-9d4905d364c3','admin','/api/data-permissions','0:0:0:0:0:0:0:1','2022-06-02 17:47:56'),(773,'7c040a2e-1817-472d-8eb3-c1dcc727fb53','admin','/api/roles/tree','0:0:0:0:0:0:0:1','2022-06-02 17:47:58'),(774,'520a9795-509c-49da-9bd4-89ba2654bc08','admin','/api/menus/tree','0:0:0:0:0:0:0:1','2022-06-02 17:47:58'),(775,'0bea8f8e-b8fe-4cdd-9c32-028e40914ecc','admin','/api/roles/tree','0:0:0:0:0:0:0:1','2022-06-02 17:47:58'),(776,'779e11e7-3b59-428a-936c-665d9cab509a','admin','/api/menus/tree','0:0:0:0:0:0:0:1','2022-06-02 17:47:58'),(777,'8547d96c-eb45-4ba0-b0b5-eb80cf545319','admin','/api/data-permissions/tree','0:0:0:0:0:0:0:1','2022-06-02 17:47:58'),(778,'648cf267-627d-4b7e-96ed-f764d3f91f46','admin','/api/data-permissions-rels/menu/permission/by/role/ROLE_ADMIN','0:0:0:0:0:0:0:1','2022-06-02 17:47:59'),(779,'3f2dd41b-bafb-41ca-8021-be24916dae83','admin','/api/data-permissions-rels/menu/permission/by/role/ROLE_ADMIN/menu/5','0:0:0:0:0:0:0:1','2022-06-02 17:48:00'),(780,'0935bcbc-ea37-4383-97f1-75c35f39c8bd','admin','/api/data-permissions-rels/menu/permission/by/role/ROLE_ADMIN/menu/6','0:0:0:0:0:0:0:1','2022-06-02 17:48:01'),(781,'c42c70f7-7ef8-473c-ab2d-49cd928a15e9','admin','/api/data-permissions-rels/menu/permission/by/role/ROLE_ADMIN/menu/4','0:0:0:0:0:0:0:1','2022-06-02 17:48:02'),(782,'a06f2e23-b7fd-49b4-82b3-e0e4ca6cca25','admin','/api/data-permissions-rels/menu/permission/by/role/ROLE_ADMIN/menu/5','0:0:0:0:0:0:0:1','2022-06-02 17:48:03'),(783,'fb4ae83f-9f0d-4ada-8568-26a021fd584d','admin','/api/menus/tree','0:0:0:0:0:0:0:1','2022-06-02 17:48:06'),(784,'15d9470f-e201-4ea8-9c26-3de8b1cf0fb0','admin','/api/roles/tree','0:0:0:0:0:0:0:1','2022-06-02 17:48:06'),(785,'5f502eac-92d4-46e2-b2d2-a25a702c95be','admin','/api/roles/tree','0:0:0:0:0:0:0:1','2022-06-02 17:48:07'),(786,'203086ef-1dfe-412d-a936-8e76cc0c1ce6','admin','/api/menus/tree','0:0:0:0:0:0:0:1','2022-06-02 17:48:07'),(787,'72eb6a0b-a3ec-4ec1-8fb0-42cae59d0b42','admin','/api/system-params','0:0:0:0:0:0:0:1','2022-06-02 17:48:12'),(788,'6935b92c-191d-4ead-8a02-36a81b905f00','admin','/api/task-params','0:0:0:0:0:0:0:1','2022-06-02 17:48:13'),(789,'54c106e0-ad56-469c-94d9-a3c1129c5ade','admin','/api/online/users','0:0:0:0:0:0:0:1','2022-06-02 17:48:16'),(790,'c2bb08b5-311b-4860-8462-83990a2b66b3','admin','/api/request-loggings','0:0:0:0:0:0:0:1','2022-06-02 17:48:17'),(791,'96721003-b435-4bae-9c04-86f4724c0963','admin','/api/slow-sql-loggings','0:0:0:0:0:0:0:1','2022-06-02 17:49:17'),(792,'579757d7-cc72-441f-b75e-e31e0d087175','admin','/api/request-loggings','0:0:0:0:0:0:0:1','2022-06-02 17:49:21'),(793,'aa2c22c0-755c-49ea-956e-4dd953eddeb5','admin','/api/online/users','0:0:0:0:0:0:0:1','2022-06-02 17:49:22'),(794,'eb035972-cc9a-432b-9f60-05cfd2c021eb','admin','/api/request-loggings','0:0:0:0:0:0:0:1','2022-06-02 17:49:24'),(795,'b96ec0bb-fbba-430a-a090-79ba491317d8','admin','/api/online/users','0:0:0:0:0:0:0:1','2022-06-02 17:49:25'),(796,'587dda0d-3985-4078-9172-c99ab39ccf15','admin','/api/request-loggings','0:0:0:0:0:0:0:1','2022-06-02 17:49:25'),(797,'725dda7a-0e43-4dfe-9350-963cebf20859','admin','/api/slow-sql-loggings','0:0:0:0:0:0:0:1','2022-06-02 17:49:26'),(798,'932fdb07-8878-4f35-bec5-a3e4a2748603','admin','/api/system-params','0:0:0:0:0:0:0:1','2022-06-02 17:49:28'),(799,'f6267d59-54c9-4822-b2f8-e1303ee4fffd','admin','/api/task-params','0:0:0:0:0:0:0:1','2022-06-02 17:49:28'),(800,'a25e1189-40ff-4c3a-be9d-20fd85cde51b','admin','/api/request-loggings','0:0:0:0:0:0:0:1','2022-06-02 17:49:32'),(801,'c9efa238-f7fd-41cb-886c-679f1c98ec05','admin','/api/online/users','0:0:0:0:0:0:0:1','2022-06-02 17:49:33'),(802,'42bc30fc-2387-4a37-9c48-d6321be2ecce','admin','/api/slow-sql-loggings','0:0:0:0:0:0:0:1','2022-06-02 17:49:34'),(803,'b2d01452-9f4d-42eb-a2b7-4b9c54fc5881','admin','/api/request-loggings','0:0:0:0:0:0:0:1','2022-06-02 17:49:35'),(804,'1e0f6725-7ffe-4665-87d5-c36e207930e4','admin','/api/ui-components','0:0:0:0:0:0:0:1','2022-06-02 17:49:39'),(805,'1ddb7854-48ec-4de3-ba08-bd6f91ee7a25','admin','/api/ui-tool-buttons','0:0:0:0:0:0:0:1','2022-06-02 17:49:40'),(806,'afacde6d-adac-4c9f-b8dd-e2ca57a6b64d','admin','/api/data-permissions','0:0:0:0:0:0:0:1','2022-06-02 17:49:43'),(807,'7eb7f654-4902-457b-a708-b6c8b599519e','admin','/api/roles/tree','0:0:0:0:0:0:0:1','2022-06-02 17:49:44'),(808,'5926da0b-dacc-43e2-90dc-d40226c671b4','admin','/api/menus/tree','0:0:0:0:0:0:0:1','2022-06-02 17:49:44'),(809,'9c904547-2313-42de-8632-a2432d34005e','admin','/api/data-permissions/tree','0:0:0:0:0:0:0:1','2022-06-02 17:49:44'),(810,'7dbb82e6-a836-4f16-b4b4-42c540d00386','admin','/api/task-params','0:0:0:0:0:0:0:1','2022-06-02 17:53:45'),(811,'ce7df105-00bc-4051-ba01-f211151c3649','admin','/api/system-params','0:0:0:0:0:0:0:1','2022-06-02 17:53:45'),(812,'23bc5e9e-1e16-4eab-9355-82b79d519329','admin','/api/online/users','0:0:0:0:0:0:0:1','2022-06-02 17:53:48'),(813,'ac6edd09-2243-49ff-bb7d-d680f2b87152','admin','/api/request-loggings','0:0:0:0:0:0:0:1','2022-06-02 17:53:48'),(814,'535a898a-fae5-4eb6-b503-2f83df599b5d','admin','/api/menus','0:0:0:0:0:0:0:1','2022-06-02 20:45:29'),(815,'cf9567f5-303e-4898-92e8-998f3dbaeeec','admin','/api/ui-components','0:0:0:0:0:0:0:1','2022-06-02 20:45:30'),(816,'714f62b0-eb47-4025-ab9c-9d78ec1ed688','admin','/api/ui-tables','0:0:0:0:0:0:0:1','2022-06-02 20:45:31'),(817,'e3da7712-2e80-47ba-9333-d3dafbfe321f','admin','/api/task-params','0:0:0:0:0:0:0:1','2022-06-02 20:45:35'),(818,'13a65c8a-3f5e-405e-8b8a-3bb5190a7681','admin','/api/system-params','0:0:0:0:0:0:0:1','2022-06-02 20:45:36'),(819,'74c26dbe-3d8e-48c5-b6f1-d3c8acc9c6fd','admin','/api/online/users','0:0:0:0:0:0:0:1','2022-06-02 20:45:42'),(820,'447d64b1-48ee-489b-9777-e6311e58d92f','admin','/api/request-loggings','0:0:0:0:0:0:0:1','2022-06-02 20:45:43'),(821,'e5a2281a-c38b-429e-9410-ce85b052f8fd','admin','/api/slow-sql-loggings','0:0:0:0:0:0:0:1','2022-06-02 20:45:46'),(822,'e7199018-4788-4632-814d-37bbe95cf198','admin','/api/request-loggings','0:0:0:0:0:0:0:1','2022-06-02 20:45:48'),(823,'8a9191f5-ee71-47f5-a8a0-13df328d08a5','admin','/api/online/users','0:0:0:0:0:0:0:1','2022-06-02 21:09:27'),(824,'b7b32acd-1812-4612-a0dc-4c68b4a56dff','admin','/api/request-loggings','0:0:0:0:0:0:0:1','2022-06-02 21:09:31');
/*!40000 ALTER TABLE `sys_request_log` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_role_menu`
--

DROP TABLE IF EXISTS `sys_role_menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_role_menu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_id` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT 'ËßíËâ≤id',
  `menu_id` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT 'ËèúÂçïid',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=224 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='ËßíËâ≤ÂØπËèúÂçï\\n@author zhaozhiwei';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_role_menu`
--

LOCK TABLES `sys_role_menu` WRITE;
/*!40000 ALTER TABLE `sys_role_menu` DISABLE KEYS */;
INSERT INTO `sys_role_menu` VALUES (187,'ROLE_ADMIN','1'),(188,'ROLE_ADMIN','2'),(189,'ROLE_ADMIN','3'),(190,'ROLE_ADMIN','4'),(191,'ROLE_ADMIN','5'),(192,'ROLE_ADMIN','6'),(193,'ROLE_ADMIN','7'),(194,'ROLE_ADMIN','9'),(195,'ROLE_ADMIN','10'),(196,'ROLE_ADMIN','11'),(197,'ROLE_ADMIN','12'),(198,'ROLE_ADMIN','13'),(199,'ROLE_ADMIN','14'),(200,'ROLE_ADMIN','15'),(201,'ROLE_ADMIN','8'),(202,'ROLE_ADMIN','16'),(203,'ROLE_ADMIN','17'),(204,'ROLE_ADMIN','18'),(205,'ROLE_ADMIN','19'),(206,'ROLE_ADMIN','20'),(207,'ROLE_ADMIN','31'),(208,'ROLE_ADMIN','21'),(209,'ROLE_ADMIN','25'),(210,'ROLE_ADMIN','26'),(211,'ROLE_ADMIN','22'),(212,'ROLE_ADMIN','23'),(213,'ROLE_ADMIN','24'),(214,'ROLE_ADMIN','30'),(215,'ROLE_ADMIN','27'),(216,'ROLE_ADMIN','28'),(217,'ROLE_ADMIN','29'),(218,'ROLE_USER','1'),(219,'ROLE_USER','2'),(220,'ROLE_USER','3'),(221,'ROLE_USER','4'),(222,'ROLE_USER','5'),(223,'ROLE_USER','6');
/*!40000 ALTER TABLE `sys_role_menu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_role_menu_button`
--

DROP TABLE IF EXISTS `sys_role_menu_button`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_role_menu_button` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_id` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT 'ËßíËâ≤id',
  `menu_id` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT 'ËèúÂçïid',
  `tool_button_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='ËßíËâ≤,ËèúÂçï,ÊåâÈíÆÊùÉÈôê\\n@author zhaozhiwei';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_role_menu_button`
--

LOCK TABLES `sys_role_menu_button` WRITE;
/*!40000 ALTER TABLE `sys_role_menu_button` DISABLE KEYS */;
INSERT INTO `sys_role_menu_button` VALUES (4,'ROLE_ADMIN','6',15);
/*!40000 ALTER TABLE `sys_role_menu_button` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_slow_sql_log`
--

DROP TABLE IF EXISTS `sys_slow_sql_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_slow_sql_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `trace_id` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT 'ËØ∑Ê±ÇÂîØ‰∏Äid, Êñπ‰æøÈóÆÈ¢òÂÆö‰Ωç',
  `jhi_current_time` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT 'ÂΩìÂâçÊó∂Èó¥',
  `jhi_sql` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT 'ÂÆåÊï¥sql',
  `execute_millis` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT 'ËøêË°åÊó∂Èó¥',
  `execute_params` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT 'ËøêË°åÊó∂Ê∂âÂèäÁöÑÂèÇÊï∞',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=124 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='ÊÖ¢sqlËÆ∞ÂΩï\\n@author zhaozhiwei';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_slow_sql_log`
--

LOCK TABLES `sys_slow_sql_log` WRITE;
/*!40000 ALTER TABLE `sys_slow_sql_log` DISABLE KEYS */;
INSERT INTO `sys_slow_sql_log` VALUES (11,'2610047d-cdfa-44b5-8f92-5f1c780b6a5b','2022-06-02 11:37:16','INSERT INTO sys_request_log (client_ip, jhi_current_time, login_name, request_uri, trace_id) VALUES (?, ?, ?, ?, ?)','423','[\"0:0:0:0:0:0:0:1\",\"2022-06-02 11:37:16\",\"admin\",\"/api/ui-components\",\"2610047d-cdfa-44b5-8f92-5f1c780b6a5b\"]'),(12,'2610047d-cdfa-44b5-8f92-5f1c780b6a5b','2022-06-02 11:37:16','INSERT INTO sys_request_log (client_ip, jhi_current_time, login_name, request_uri, trace_id) VALUES (?, ?, ?, ?, ?)','455','[\"0:0:0:0:0:0:0:1\",\"2022-06-02 11:37:16\",\"admin\",\"/api/ui-components\",\"2610047d-cdfa-44b5-8f92-5f1c780b6a5b\"]'),(13,'6848a072-9097-495a-9991-d73389588ec6','2022-06-02 11:37:17','INSERT INTO sys_request_log (client_ip, jhi_current_time, login_name, request_uri, trace_id) VALUES (?, ?, ?, ?, ?)','273','[\"0:0:0:0:0:0:0:1\",\"2022-06-02 11:37:17\",\"admin\",\"/api/ui-tool-buttons\",\"6848a072-9097-495a-9991-d73389588ec6\"]'),(14,'6848a072-9097-495a-9991-d73389588ec6','2022-06-02 11:37:17','INSERT INTO sys_request_log (client_ip, jhi_current_time, login_name, request_uri, trace_id) VALUES (?, ?, ?, ?, ?)','285','[\"0:0:0:0:0:0:0:1\",\"2022-06-02 11:37:17\",\"admin\",\"/api/ui-tool-buttons\",\"6848a072-9097-495a-9991-d73389588ec6\"]'),(15,NULL,'2022-06-02 11:38:06','select 		RES.* 		from ACT_RU_JOB RES 		where LOCK_EXP_TIME_ is null 		LIMIT ? OFFSET ?','212','[1,0]'),(16,NULL,'2022-06-02 11:38:06','select 		RES.* 		from ACT_RU_JOB RES 		where LOCK_EXP_TIME_ is null 		LIMIT ? OFFSET ?','235','[1,0]'),(17,NULL,'2022-06-02 11:38:58','select 		RES.* 		from ACT_RU_JOB RES 		where LOCK_EXP_TIME_ is null 		LIMIT ? OFFSET ?','205','[1,0]'),(18,NULL,'2022-06-02 11:38:58','select 		RES.* 		from ACT_RU_TIMER_JOB RES 		where DUEDATE_ <= ? 		and LOCK_OWNER_ is null 		LIMIT ? OFFSET ?','207','[\"2022-06-02 11:38:58\",1,0]'),(19,NULL,'2022-06-02 11:38:58','select 		RES.* 		from ACT_RU_JOB RES 		where LOCK_EXP_TIME_ is null 		LIMIT ? OFFSET ?','215','[1,0]'),(20,NULL,'2022-06-02 11:38:58','select 		RES.* 		from ACT_RU_TIMER_JOB RES 		where DUEDATE_ <= ? 		and LOCK_OWNER_ is null 		LIMIT ? OFFSET ?','217','[\"2022-06-02 11:38:58\",1,0]'),(21,NULL,'2022-06-02 11:39:39','select 		RES.* 		from ACT_RU_JOB RES 		where LOCK_EXP_TIME_ is null 		LIMIT ? OFFSET ?','263','[1,0]'),(22,NULL,'2022-06-02 11:39:39','select 		RES.* 		from ACT_RU_JOB RES 		where LOCK_EXP_TIME_ is null 		LIMIT ? OFFSET ?','272','[1,0]'),(23,'009685df-159d-4eb5-a3a0-e0a2a6d723b8','2022-06-02 11:39:41','INSERT INTO sys_request_log (client_ip, jhi_current_time, login_name, request_uri, trace_id) VALUES (?, ?, ?, ?, ?)','370','[\"0:0:0:0:0:0:0:1\",\"2022-06-02 11:39:41\",\"admin\",\"/api/menus\",\"009685df-159d-4eb5-a3a0-e0a2a6d723b8\"]'),(24,'009685df-159d-4eb5-a3a0-e0a2a6d723b8','2022-06-02 11:39:41','INSERT INTO sys_request_log (client_ip, jhi_current_time, login_name, request_uri, trace_id) VALUES (?, ?, ?, ?, ?)','378','[\"0:0:0:0:0:0:0:1\",\"2022-06-02 11:39:41\",\"admin\",\"/api/menus\",\"009685df-159d-4eb5-a3a0-e0a2a6d723b8\"]'),(25,NULL,'2022-06-02 11:40:02','select 		RES.* 		from ACT_RU_TIMER_JOB RES 		where DUEDATE_ <= ? 		and LOCK_OWNER_ is null 		LIMIT ? OFFSET ?','2770','[\"2022-06-02 11:39:59\",1,0]'),(26,NULL,'2022-06-02 11:40:02','select 		RES.* 		from ACT_RU_TIMER_JOB RES 		where DUEDATE_ <= ? 		and LOCK_OWNER_ is null 		LIMIT ? OFFSET ?','2782','[\"2022-06-02 11:39:59\",1,0]'),(27,NULL,'2022-06-02 11:40:00','select 		RES.* 		from ACT_RU_JOB RES 		where LOCK_EXP_TIME_ is null 		LIMIT ? OFFSET ?','1043','[1,0]'),(28,NULL,'2022-06-02 11:40:02','INSERT INTO sys_slow_sql_log (jhi_current_time, execute_millis, execute_params, jhi_sql, trace_id) VALUES (?, ?, ?, ?, ?)','1942','[\"2022-06-02 11:40:00\",\"1043\",\"[1,0]\",\"select \\t\\tRES.* \\t\\tfrom ACT_RU_JOB RES \\t\\twhere LOCK_EXP_TIME_ is null \\t\\tLIMIT ? OFFSET ?\",null]'),(29,NULL,'2022-06-02 11:40:02','INSERT INTO sys_slow_sql_log (jhi_current_time, execute_millis, execute_params, jhi_sql, trace_id) VALUES (?, ?, ?, ?, ?)','1957','[\"2022-06-02 11:40:00\",\"1043\",\"[1,0]\",\"select \\t\\tRES.* \\t\\tfrom ACT_RU_JOB RES \\t\\twhere LOCK_EXP_TIME_ is null \\t\\tLIMIT ? OFFSET ?\",null]'),(30,NULL,'2022-06-02 11:40:02','select 		RES.* 		from ACT_RU_JOB RES 		where LOCK_EXP_TIME_ is null 		LIMIT ? OFFSET ?','3014','[1,0]'),(31,NULL,'2022-06-02 11:43:06','select 	RES.* 	from ACT_RU_JOB RES 	where RES.LOCK_EXP_TIME_ is not null and RES.LOCK_EXP_TIME_ < ? 	LIMIT ? OFFSET ?','213','[\"2022-06-02 11:43:06\",3,0]'),(32,NULL,'2022-06-02 11:43:06','select 	RES.* 	from ACT_RU_JOB RES 	where RES.LOCK_EXP_TIME_ is not null and RES.LOCK_EXP_TIME_ < ? 	LIMIT ? OFFSET ?','224','[\"2022-06-02 11:43:06\",3,0]'),(33,'88ffaccb-c199-4647-839b-cb04414b21ca','2022-06-02 11:43:16','INSERT INTO sys_request_log (client_ip, jhi_current_time, login_name, request_uri, trace_id) VALUES (?, ?, ?, ?, ?)','306','[\"0:0:0:0:0:0:0:1\",\"2022-06-02 11:43:16\",\"admin\",\"/api/menus\",\"88ffaccb-c199-4647-839b-cb04414b21ca\"]'),(34,'88ffaccb-c199-4647-839b-cb04414b21ca','2022-06-02 11:43:16','INSERT INTO sys_request_log (client_ip, jhi_current_time, login_name, request_uri, trace_id) VALUES (?, ?, ?, ?, ?)','317','[\"0:0:0:0:0:0:0:1\",\"2022-06-02 11:43:16\",\"admin\",\"/api/menus\",\"88ffaccb-c199-4647-839b-cb04414b21ca\"]'),(35,'516558e0-d1a8-46b9-9bed-f471de2bac0d','2022-06-02 11:43:19','INSERT INTO sys_request_log (client_ip, jhi_current_time, login_name, request_uri, trace_id) VALUES (?, ?, ?, ?, ?)','313','[\"0:0:0:0:0:0:0:1\",\"2022-06-02 11:43:19\",\"admin\",\"/api/account\",\"516558e0-d1a8-46b9-9bed-f471de2bac0d\"]'),(36,'516558e0-d1a8-46b9-9bed-f471de2bac0d','2022-06-02 11:43:19','INSERT INTO sys_request_log (client_ip, jhi_current_time, login_name, request_uri, trace_id) VALUES (?, ?, ?, ?, ?)','321','[\"0:0:0:0:0:0:0:1\",\"2022-06-02 11:43:19\",\"admin\",\"/api/account\",\"516558e0-d1a8-46b9-9bed-f471de2bac0d\"]'),(37,NULL,'2022-06-02 11:43:26','select 		RES.* 		from ACT_RU_JOB RES 		where LOCK_EXP_TIME_ is null 		LIMIT ? OFFSET ?','327','[1,0]'),(38,NULL,'2022-06-02 11:43:26','select 		RES.* 		from ACT_RU_JOB RES 		where LOCK_EXP_TIME_ is null 		LIMIT ? OFFSET ?','339','[1,0]'),(39,NULL,'2022-06-02 11:43:36','select 		RES.* 		from ACT_RU_TIMER_JOB RES 		where DUEDATE_ <= ? 		and LOCK_OWNER_ is null 		LIMIT ? OFFSET ?','317','[\"2022-06-02 11:43:36\",1,0]'),(40,NULL,'2022-06-02 11:43:36','select 		RES.* 		from ACT_RU_TIMER_JOB RES 		where DUEDATE_ <= ? 		and LOCK_OWNER_ is null 		LIMIT ? OFFSET ?','326','[\"2022-06-02 11:43:36\",1,0]'),(41,'79752227-a341-4d59-a126-21d3f97cf003','2022-06-02 11:43:48','INSERT INTO sys_request_log (client_ip, jhi_current_time, login_name, request_uri, trace_id) VALUES (?, ?, ?, ?, ?)','233','[\"0:0:0:0:0:0:0:1\",\"2022-06-02 11:43:48\",\"admin\",\"/api/menus/32\",\"79752227-a341-4d59-a126-21d3f97cf003\"]'),(42,'79752227-a341-4d59-a126-21d3f97cf003','2022-06-02 11:43:48','INSERT INTO sys_request_log (client_ip, jhi_current_time, login_name, request_uri, trace_id) VALUES (?, ?, ?, ?, ?)','244','[\"0:0:0:0:0:0:0:1\",\"2022-06-02 11:43:48\",\"admin\",\"/api/menus/32\",\"79752227-a341-4d59-a126-21d3f97cf003\"]'),(43,'450a5f55-e4c0-42da-be22-ce22e8cbd9b2','2022-06-02 11:43:54','INSERT INTO sys_request_log (client_ip, jhi_current_time, login_name, request_uri, trace_id) VALUES (?, ?, ?, ?, ?)','245','[\"0:0:0:0:0:0:0:1\",\"2022-06-02 11:43:54\",\"admin\",\"/api/slow-sql-loggings\",\"450a5f55-e4c0-42da-be22-ce22e8cbd9b2\"]'),(44,'450a5f55-e4c0-42da-be22-ce22e8cbd9b2','2022-06-02 11:43:54','INSERT INTO sys_request_log (client_ip, jhi_current_time, login_name, request_uri, trace_id) VALUES (?, ?, ?, ?, ?)','260','[\"0:0:0:0:0:0:0:1\",\"2022-06-02 11:43:54\",\"admin\",\"/api/slow-sql-loggings\",\"450a5f55-e4c0-42da-be22-ce22e8cbd9b2\"]'),(45,NULL,'2022-06-02 11:43:57','select 		RES.* 		from ACT_RU_JOB RES 		where LOCK_EXP_TIME_ is null 		LIMIT ? OFFSET ?','523','[1,0]'),(46,NULL,'2022-06-02 11:43:57','select 		RES.* 		from ACT_RU_JOB RES 		where LOCK_EXP_TIME_ is null 		LIMIT ? OFFSET ?','528','[1,0]'),(47,NULL,'2022-06-02 11:44:27','select 		RES.* 		from ACT_RU_JOB RES 		where LOCK_EXP_TIME_ is null 		LIMIT ? OFFSET ?','212','[1,0]'),(48,NULL,'2022-06-02 11:44:27','select 		RES.* 		from ACT_RU_JOB RES 		where LOCK_EXP_TIME_ is null 		LIMIT ? OFFSET ?','217','[1,0]'),(49,'fe3df372-04c8-43ed-bdaa-220b0c0ddd85','2022-06-02 11:44:42','INSERT INTO sys_request_log (client_ip, jhi_current_time, login_name, request_uri, trace_id) VALUES (?, ?, ?, ?, ?)','206','[\"0:0:0:0:0:0:0:1\",\"2022-06-02 11:44:42\",\"admin\",\"/api/request-loggings\",\"fe3df372-04c8-43ed-bdaa-220b0c0ddd85\"]'),(50,'fe3df372-04c8-43ed-bdaa-220b0c0ddd85','2022-06-02 11:44:42','INSERT INTO sys_request_log (client_ip, jhi_current_time, login_name, request_uri, trace_id) VALUES (?, ?, ?, ?, ?)','220','[\"0:0:0:0:0:0:0:1\",\"2022-06-02 11:44:42\",\"admin\",\"/api/request-loggings\",\"fe3df372-04c8-43ed-bdaa-220b0c0ddd85\"]'),(51,'027930a3-64c8-4e4d-8ec1-35c3e375db4c','2022-06-02 11:44:43','INSERT INTO sys_request_log (client_ip, jhi_current_time, login_name, request_uri, trace_id) VALUES (?, ?, ?, ?, ?)','207','[\"0:0:0:0:0:0:0:1\",\"2022-06-02 11:44:43\",\"admin\",\"/api/slow-sql-loggings\",\"027930a3-64c8-4e4d-8ec1-35c3e375db4c\"]'),(52,'027930a3-64c8-4e4d-8ec1-35c3e375db4c','2022-06-02 11:44:43','INSERT INTO sys_request_log (client_ip, jhi_current_time, login_name, request_uri, trace_id) VALUES (?, ?, ?, ?, ?)','217','[\"0:0:0:0:0:0:0:1\",\"2022-06-02 11:44:43\",\"admin\",\"/api/slow-sql-loggings\",\"027930a3-64c8-4e4d-8ec1-35c3e375db4c\"]'),(53,NULL,'2022-06-02 11:45:08','select 		RES.* 		from ACT_RU_JOB RES 		where LOCK_EXP_TIME_ is null 		LIMIT ? OFFSET ?','471','[1,0]'),(54,NULL,'2022-06-02 11:45:08','select 		RES.* 		from ACT_RU_JOB RES 		where LOCK_EXP_TIME_ is null 		LIMIT ? OFFSET ?','484','[1,0]'),(55,NULL,'2022-06-02 12:07:12','select 		RES.* 		from ACT_RU_TIMER_JOB RES 		where DUEDATE_ <= ? 		and LOCK_OWNER_ is null 		LIMIT ? OFFSET ?','1033','[\"2022-06-02 12:07:11\",1,0]'),(56,NULL,'2022-06-02 12:07:14','INSERT INTO sys_slow_sql_log (jhi_current_time, execute_millis, execute_params, jhi_sql, trace_id) VALUES (?, ?, ?, ?, ?)','1897','[\"2022-06-02 12:07:12\",\"1033\",\"[\\\"2022-06-02 12:07:11\\\",1,0]\",\"select \\t\\tRES.* \\t\\tfrom ACT_RU_TIMER_JOB RES \\t\\twhere DUEDATE_ <= ? \\t\\tand LOCK_OWNER_ is null \\t\\tLIMI...\",null]'),(57,NULL,'2022-06-02 12:07:14','INSERT INTO sys_slow_sql_log (jhi_current_time, execute_millis, execute_params, jhi_sql, trace_id) VALUES (?, ?, ?, ?, ?)','1909','[\"2022-06-02 12:07:12\",\"1033\",\"[\\\"2022-06-02 12:07:11\\\",1,0]\",\"select \\t\\tRES.* \\t\\tfrom ACT_RU_TIMER_JOB RES \\t\\twhere DUEDATE_ <= ? \\t\\tand LOCK_OWNER_ is null \\t\\tLIMI...\",null]'),(58,NULL,'2022-06-02 12:07:14','select 		RES.* 		from ACT_RU_TIMER_JOB RES 		where DUEDATE_ <= ? 		and LOCK_OWNER_ is null 		LIMIT ? OFFSET ?','2969','[\"2022-06-02 12:07:11\",1,0]'),(59,NULL,'2022-06-02 13:09:55','select 		RES.* 		from ACT_RU_JOB RES 		where LOCK_EXP_TIME_ is null 		LIMIT ? OFFSET ?','6263','[1,0]'),(60,NULL,'2022-06-02 13:09:55','select 		RES.* 		from ACT_RU_JOB RES 		where LOCK_EXP_TIME_ is null 		LIMIT ? OFFSET ?','6317','[1,0]'),(61,NULL,'2022-06-02 13:20:09','select 		RES.* 		from ACT_RU_TIMER_JOB RES 		where DUEDATE_ <= ? 		and LOCK_OWNER_ is null 		LIMIT ? OFFSET ?','1895','[\"2022-06-02 13:20:06\",1,0]'),(62,NULL,'2022-06-02 13:20:09','select 		RES.* 		from ACT_RU_TIMER_JOB RES 		where DUEDATE_ <= ? 		and LOCK_OWNER_ is null 		LIMIT ? OFFSET ?','2593','[\"2022-06-02 13:20:06\",1,0]'),(63,NULL,'2022-06-02 14:12:02','select 		RES.* 		from ACT_RU_TIMER_JOB RES 		where DUEDATE_ <= ? 		and LOCK_OWNER_ is null 		LIMIT ? OFFSET ?','4219','[\"2022-06-02 14:11:57\",1,0]'),(64,NULL,'2022-06-02 14:12:03','select 		RES.* 		from ACT_RU_TIMER_JOB RES 		where DUEDATE_ <= ? 		and LOCK_OWNER_ is null 		LIMIT ? OFFSET ?','4883','[\"2022-06-02 14:11:57\",1,0]'),(65,NULL,'2022-06-02 17:58:46','select 		RES.* 		from ACT_RU_TIMER_JOB RES 		where DUEDATE_ <= ? 		and LOCK_OWNER_ is null 		LIMIT ? OFFSET ?','2497','[\"2022-06-02 17:58:44\",1,0]'),(66,NULL,'2022-06-02 17:58:46','select 		RES.* 		from ACT_RU_TIMER_JOB RES 		where DUEDATE_ <= ? 		and LOCK_OWNER_ is null 		LIMIT ? OFFSET ?','2514','[\"2022-06-02 17:58:44\",1,0]'),(67,NULL,'2022-06-02 18:45:40','select 	RES.* 	from ACT_RU_JOB RES 	where RES.LOCK_EXP_TIME_ is not null and RES.LOCK_EXP_TIME_ < ? 	LIMIT ? OFFSET ?','1193','[\"2022-06-02 18:45:39\",3,0]'),(68,NULL,'2022-06-02 18:45:40','select 	RES.* 	from ACT_RU_JOB RES 	where RES.LOCK_EXP_TIME_ is not null and RES.LOCK_EXP_TIME_ < ? 	LIMIT ? OFFSET ?','1210','[\"2022-06-02 18:45:39\",3,0]'),(69,NULL,'2022-06-02 20:19:16','select 		RES.* 		from ACT_RU_TIMER_JOB RES 		where DUEDATE_ <= ? 		and LOCK_OWNER_ is null 		LIMIT ? OFFSET ?','1864','[\"2022-06-02 20:19:14\",1,0]'),(70,NULL,'2022-06-02 20:19:17','select 		RES.* 		from ACT_RU_JOB RES 		where LOCK_EXP_TIME_ is null 		LIMIT ? OFFSET ?','2917','[1,0]'),(71,NULL,'2022-06-02 20:19:17','select 		RES.* 		from ACT_RU_TIMER_JOB RES 		where DUEDATE_ <= ? 		and LOCK_OWNER_ is null 		LIMIT ? OFFSET ?','2619','[\"2022-06-02 20:19:14\",1,0]'),(72,NULL,'2022-06-02 20:19:18','select 		RES.* 		from ACT_RU_JOB RES 		where LOCK_EXP_TIME_ is null 		LIMIT ? OFFSET ?','3689','[1,0]'),(73,NULL,'2022-06-02 20:19:23','insert into sys_slow_sql_log (jhi_current_time, execute_millis, execute_params, jhi_sql, trace_id) values (?, ?, ?, ?, ?)','5367','[\"2022-06-02 20:19:18\",\"3689\",\"[1,0]\",\"select \\t\\tRES.* \\t\\tfrom ACT_RU_JOB RES \\t\\twhere LOCK_EXP_TIME_ is null \\t\\tLIMIT ? OFFSET ?\",null]'),(74,NULL,'2022-06-02 20:19:24','insert into sys_slow_sql_log (jhi_current_time, execute_millis, execute_params, jhi_sql, trace_id) values (?, ?, ?, ?, ?)','6136','[\"2022-06-02 20:19:18\",\"3689\",\"[1,0]\",\"select \\t\\tRES.* \\t\\tfrom ACT_RU_JOB RES \\t\\twhere LOCK_EXP_TIME_ is null \\t\\tLIMIT ? OFFSET ?\",null]'),(84,NULL,'2022-06-02 20:23:23','select 		RES.* 		from ACT_RU_JOB RES 		where LOCK_EXP_TIME_ is null 		LIMIT ? OFFSET ?','10880','[1,0]'),(85,NULL,'2022-06-02 20:23:23','select 		RES.* 		from ACT_RU_JOB RES 		where LOCK_EXP_TIME_ is null 		LIMIT ? OFFSET ?','10889','[1,0]'),(88,NULL,'2022-06-02 21:00:32','select 		RES.* 		from ACT_RU_JOB RES 		where LOCK_EXP_TIME_ is null 		LIMIT ? OFFSET ?','11122','[1,0]'),(89,NULL,'2022-06-02 21:00:33','select 		RES.* 		from ACT_RU_JOB RES 		where LOCK_EXP_TIME_ is null 		LIMIT ? OFFSET ?','12046','[1,0]'),(90,NULL,'2022-06-02 21:00:34','insert into sys_slow_sql_log (jhi_current_time, execute_millis, execute_params, jhi_sql, trace_id) values (?, ?, ?, ?, ?)','1390','[\"2022-06-02 21:00:33\",\"12046\",\"[1,0]\",\"select \\t\\tRES.* \\t\\tfrom ACT_RU_JOB RES \\t\\twhere LOCK_EXP_TIME_ is null \\t\\tLIMIT ? OFFSET ?\",null]'),(91,NULL,'2022-06-02 21:00:36','insert into sys_slow_sql_log (jhi_current_time, execute_millis, execute_params, jhi_sql, trace_id) values (?, ?, ?, ?, ?)','2210','[\"2022-06-02 21:00:34\",\"1390\",\"[\\\"2022-06-02 21:00:33\\\",\\\"12046\\\",\\\"[1,0]\\\",\\\"select \\\\t\\\\tRES.* \\\\t\\\\tfrom ACT_RU_JOB RES \\\\t\\\\twhere LOCK_E...\",\"insert into sys_slow_sql_log (jhi_current_time, execute_millis, execute_params, jhi_sql, trace_id...\",null]'),(92,NULL,'2022-06-02 21:00:36','insert into sys_slow_sql_log (jhi_current_time, execute_millis, execute_params, jhi_sql, trace_id) values (?, ?, ?, ?, ?)','2326','[\"2022-06-02 21:00:34\",\"1390\",\"[\\\"2022-06-02 21:00:33\\\",\\\"12046\\\",\\\"[1,0]\\\",\\\"select \\\\t\\\\tRES.* \\\\t\\\\tfrom ACT_RU_JOB RES \\\\t\\\\twhere LOCK_E...\",\"insert into sys_slow_sql_log (jhi_current_time, execute_millis, execute_params, jhi_sql, trace_id...\",null]'),(93,NULL,'2022-06-02 21:00:36','insert into sys_slow_sql_log (jhi_current_time, execute_millis, execute_params, jhi_sql, trace_id) values (?, ?, ?, ?, ?)','3756','[\"2022-06-02 21:00:33\",\"12046\",\"[1,0]\",\"select \\t\\tRES.* \\t\\tfrom ACT_RU_JOB RES \\t\\twhere LOCK_EXP_TIME_ is null \\t\\tLIMIT ? OFFSET ?\",null]'),(94,NULL,'2022-06-02 21:00:55','select 		RES.* 		from ACT_RU_TIMER_JOB RES 		where DUEDATE_ <= ? 		and LOCK_OWNER_ is null 		LIMIT ? OFFSET ?','58513','[\"2022-06-02 20:59:56\",1,0]'),(95,NULL,'2022-06-02 21:00:55','select 		RES.* 		from ACT_RU_TIMER_JOB RES 		where DUEDATE_ <= ? 		and LOCK_OWNER_ is null 		LIMIT ? OFFSET ?','58531','[\"2022-06-02 20:59:56\",1,0]'),(96,NULL,'2022-06-02 21:01:16','select 		RES.* 		from ACT_RU_JOB RES 		where LOCK_EXP_TIME_ is null 		LIMIT ? OFFSET ?','1028','[1,0]'),(97,NULL,'2022-06-02 21:01:16','select 		RES.* 		from ACT_RU_TIMER_JOB RES 		where DUEDATE_ <= ? 		and LOCK_OWNER_ is null 		LIMIT ? OFFSET ?','9442','[\"2022-06-02 21:01:06\",1,0]'),(98,NULL,'2022-06-02 21:01:16','select 		RES.* 		from ACT_RU_JOB RES 		where LOCK_EXP_TIME_ is null 		LIMIT ? OFFSET ?','1474','[1,0]'),(99,NULL,'2022-06-02 21:01:17','select 		RES.* 		from ACT_RU_TIMER_JOB RES 		where DUEDATE_ <= ? 		and LOCK_OWNER_ is null 		LIMIT ? OFFSET ?','10242','[\"2022-06-02 21:01:06\",1,0]'),(100,NULL,'2022-06-02 21:02:04','select 		RES.* 		from ACT_RU_JOB RES 		where LOCK_EXP_TIME_ is null 		LIMIT ? OFFSET ?','2727','[1,0]'),(101,NULL,'2022-06-02 21:02:05','select 		RES.* 		from ACT_RU_JOB RES 		where LOCK_EXP_TIME_ is null 		LIMIT ? OFFSET ?','3393','[1,0]'),(102,NULL,'2022-06-02 21:03:16','select 		RES.* 		from ACT_RU_JOB RES 		where LOCK_EXP_TIME_ is null 		LIMIT ? OFFSET ?','1576','[1,0]'),(103,NULL,'2022-06-02 21:03:23','select 	RES.* 	from ACT_RU_JOB RES 	where RES.LOCK_EXP_TIME_ is not null and RES.LOCK_EXP_TIME_ < ? 	LIMIT ? OFFSET ?','1488','[\"2022-06-02 21:03:22\",3,0]'),(104,NULL,'2022-06-02 21:03:27','insert into sys_slow_sql_log (jhi_current_time, execute_millis, execute_params, jhi_sql, trace_id) values (?, ?, ?, ?, ?)','4144','[\"2022-06-02 21:03:23\",\"1488\",\"[\\\"2022-06-02 21:03:22\\\",3,0]\",\"select \\tRES.* \\tfrom ACT_RU_JOB RES \\twhere RES.LOCK_EXP_TIME_ is not null and RES.LOCK_EXP_TIME_ <...\",null]'),(105,NULL,'2022-06-02 21:03:27','insert into sys_slow_sql_log (jhi_current_time, execute_millis, execute_params, jhi_sql, trace_id) values (?, ?, ?, ?, ?)','4237','[\"2022-06-02 21:03:23\",\"1488\",\"[\\\"2022-06-02 21:03:22\\\",3,0]\",\"select \\tRES.* \\tfrom ACT_RU_JOB RES \\twhere RES.LOCK_EXP_TIME_ is not null and RES.LOCK_EXP_TIME_ <...\",null]'),(106,NULL,'2022-06-02 21:03:27','select 	RES.* 	from ACT_RU_JOB RES 	where RES.LOCK_EXP_TIME_ is not null and RES.LOCK_EXP_TIME_ < ? 	LIMIT ? OFFSET ?','5740','[\"2022-06-02 21:03:22\",3,0]'),(107,NULL,'2022-06-02 21:03:29','insert into sys_slow_sql_log (jhi_current_time, execute_millis, execute_params, jhi_sql, trace_id) values (?, ?, ?, ?, ?)','12665','[\"2022-06-02 21:03:16\",\"1576\",\"[1,0]\",\"select \\t\\tRES.* \\t\\tfrom ACT_RU_JOB RES \\t\\twhere LOCK_EXP_TIME_ is null \\t\\tLIMIT ? OFFSET ?\",null]'),(108,NULL,'2022-06-02 21:03:29','insert into sys_slow_sql_log (jhi_current_time, execute_millis, execute_params, jhi_sql, trace_id) values (?, ?, ?, ?, ?)','12873','[\"2022-06-02 21:03:16\",\"1576\",\"[1,0]\",\"select \\t\\tRES.* \\t\\tfrom ACT_RU_JOB RES \\t\\twhere LOCK_EXP_TIME_ is null \\t\\tLIMIT ? OFFSET ?\",null]'),(109,NULL,'2022-06-02 21:03:29','select 		RES.* 		from ACT_RU_JOB RES 		where LOCK_EXP_TIME_ is null 		LIMIT ? OFFSET ?','14464','[1,0]'),(112,NULL,'2022-06-02 21:05:01','select 		RES.* 		from ACT_RU_JOB RES 		where LOCK_EXP_TIME_ is null 		LIMIT ? OFFSET ?','2027','[1,0]'),(113,NULL,'2022-06-02 21:05:02','select 		RES.* 		from ACT_RU_JOB RES 		where LOCK_EXP_TIME_ is null 		LIMIT ? OFFSET ?','2402','[1,0]'),(114,NULL,'2022-06-02 21:05:33','select 		RES.* 		from ACT_RU_TIMER_JOB RES 		where DUEDATE_ <= ? 		and LOCK_OWNER_ is null 		LIMIT ? OFFSET ?','2534','[\"2022-06-02 21:05:30\",1,0]'),(115,NULL,'2022-06-02 21:05:34','select 		RES.* 		from ACT_RU_TIMER_JOB RES 		where DUEDATE_ <= ? 		and LOCK_OWNER_ is null 		LIMIT ? OFFSET ?','3365','[\"2022-06-02 21:05:30\",1,0]'),(116,NULL,'2022-06-02 21:05:59','select 		RES.* 		from ACT_RU_JOB RES 		where LOCK_EXP_TIME_ is null 		LIMIT ? OFFSET ?','1097','[1,0]'),(117,NULL,'2022-06-02 21:05:59','select 		RES.* 		from ACT_RU_JOB RES 		where LOCK_EXP_TIME_ is null 		LIMIT ? OFFSET ?','1418','[1,0]'),(118,NULL,'2022-06-02 21:06:00','select 		RES.* 		from ACT_RU_TIMER_JOB RES 		where DUEDATE_ <= ? 		and LOCK_OWNER_ is null 		LIMIT ? OFFSET ?','1143','[\"2022-06-02 21:05:58\",1,0]'),(119,NULL,'2022-06-02 21:06:00','select 		RES.* 		from ACT_RU_TIMER_JOB RES 		where DUEDATE_ <= ? 		and LOCK_OWNER_ is null 		LIMIT ? OFFSET ?','1240','[\"2022-06-02 21:05:58\",1,0]'),(120,NULL,'2022-06-02 21:07:12','select 		RES.* 		from ACT_RU_JOB RES 		where LOCK_EXP_TIME_ is null 		LIMIT ? OFFSET ?','61449','[1,0]'),(121,NULL,'2022-06-02 21:07:12','select 		RES.* 		from ACT_RU_JOB RES 		where LOCK_EXP_TIME_ is null 		LIMIT ? OFFSET ?','61460','[1,0]'),(122,NULL,'2022-06-02 21:07:50','select 		RES.* 		from ACT_RU_TIMER_JOB RES 		where DUEDATE_ <= ? 		and LOCK_OWNER_ is null 		LIMIT ? OFFSET ?','99000','[\"2022-06-02 21:06:11\",1,0]'),(123,NULL,'2022-06-02 21:07:50','select 		RES.* 		from ACT_RU_TIMER_JOB RES 		where DUEDATE_ <= ? 		and LOCK_OWNER_ is null 		LIMIT ? OFFSET ?','99008','[\"2022-06-02 21:06:11\",1,0]');
/*!40000 ALTER TABLE `sys_slow_sql_log` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_task_param`
--

DROP TABLE IF EXISTS `sys_task_param`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_task_param` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT 'ÂÆöÊó∂‰ªªÂä°ÂêçÁß∞',
  `cron_expression` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT 'Ë°®ËææÂºè',
  `start_class` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT 'ÂÆöÊó∂‰ªªÂä°ÂÖ•Âè£',
  `enable` bit(1) DEFAULT NULL COMMENT 'ÊòØÂê¶ÂêØÁî®',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='ÂÆöÊó∂‰ªªÂä°ÈÖçÁΩÆ‰ø°ÊÅØ\\n@author zhaozhiwei';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_task_param`
--

LOCK TABLES `sys_task_param` WRITE;
/*!40000 ALTER TABLE `sys_task_param` DISABLE KEYS */;
INSERT INTO `sys_task_param` VALUES (11,'ÂÆöÊó∂‰ªªÂä°ÊµãËØï','0/5 * * * * ?','com.example.service.timetask.Job1#execute','');
/*!40000 ALTER TABLE `sys_task_param` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_uicomponent`
--

DROP TABLE IF EXISTS `sys_uicomponent`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_uicomponent` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `menuid` bigint(20) DEFAULT NULL,
  `ordernum` int(11) DEFAULT NULL,
  `componentid` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `config` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_uicomponent`
--

LOCK TABLES `sys_uicomponent` WRITE;
/*!40000 ALTER TABLE `sys_uicomponent` DISABLE KEYS */;
INSERT INTO `sys_uicomponent` VALUES (11,0,1,'uieditform','{}'),(12,0,2,'uitoolbutton','{}'),(13,0,4,'uitable','{}'),(14,5,1,'uieditform','{isinline:false}'),(15,5,2,'uitoolbutton','{}'),(16,6,1,'uitoolbutton','{}'),(17,6,2,'uitable','{}'),(18,0,3,'uitab','{}');
/*!40000 ALTER TABLE `sys_uicomponent` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_uieditform`
--

DROP TABLE IF EXISTS `sys_uieditform`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_uieditform` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `menuid` bigint(20) DEFAULT NULL,
  `code` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `ordernum` int(11) DEFAULT NULL,
  `issource` bit(1) DEFAULT NULL,
  `isedit` bit(1) DEFAULT NULL,
  `requirement` bit(1) DEFAULT NULL,
  `type` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `placeholder` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `config` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_uieditform`
--

LOCK TABLES `sys_uieditform` WRITE;
/*!40000 ALTER TABLE `sys_uieditform` DISABLE KEYS */;
INSERT INTO `sys_uieditform` VALUES (12,0,'name','ÂßìÂêç',1,'\0','','','input','ËØ∑ËæìÂÖ•ÂßìÂêç','{}'),(13,0,'sex','ÊÄßÂà´',2,'\0','','','input','ËØ∑ËæìÂÖ•ÊÄßÂà´','{}'),(14,0,'age','Âπ¥ÈæÑ',3,'\0','','','input','ËØ∑ËæìÂÖ•Âπ¥ÈæÑ','{}'),(15,5,'type','ËØ∑ÂÅáÁ±ªÂûã',1,'','','','select','ËØ∑ÈÄâÊã©','{eleBeanName:\"leaveTypeRepository\"}'),(16,5,'startTime','ÂºÄÂßãÊó∂Èó¥',2,'\0','','\0','date','ËØ∑ÈÄâÊã©ÂºÄÂßãÊó∂Èó¥','{}'),(17,5,'endTime','ÁªìÊùüÊó∂Èó¥',3,'\0','','\0','date','ËØ∑ÈÄâÊã©ÁªìÊùüÊó∂Èó¥','{}'),(18,5,'reason','ËØ∑ÂÅá‰∫ãÁî±',4,'\0','','\0','textarea','ËØ∑ËæìÂÖ•ËØ∑ÂÅá‰∫ãÁî±','{}'),(19,5,'file','ËØ¥ÊòéÈôÑ‰ª∂',5,'\0','','\0','input','ËØ∑ÈÄâÊã©ÈôÑ‰ª∂','{}'),(20,5,'superior','Áõ¥Êé•‰∏äÁ∫ß',6,'','','\0','select','ËØ∑ÈÄâÊã©','{eleBeanName:\"userRepository\"}');
/*!40000 ALTER TABLE `sys_uieditform` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_uiqueryform`
--

DROP TABLE IF EXISTS `sys_uiqueryform`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_uiqueryform` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `menuid` bigint(20) DEFAULT NULL,
  `code` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `ordernum` int(11) DEFAULT NULL,
  `issource` bit(1) DEFAULT NULL,
  `requirement` bit(1) DEFAULT NULL,
  `type` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `placeholder` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `config` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_uiqueryform`
--

LOCK TABLES `sys_uiqueryform` WRITE;
/*!40000 ALTER TABLE `sys_uiqueryform` DISABLE KEYS */;
/*!40000 ALTER TABLE `sys_uiqueryform` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_uitab`
--

DROP TABLE IF EXISTS `sys_uitab`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_uitab` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `menuid` bigint(20) DEFAULT NULL,
  `code` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT 'È°µÁ≠æÁöÑÊòæÁ§∫ÁºñÁ†Å, ÂîØ‰∏Ä,Ê†πÊçÆcode‰∏öÂä°ÂÅöËá™Â∑±ÁöÑÂ§ÑÁêÜ',
  `name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT 'È°µÁ≠æÊòæÁ§∫ÁöÑÊ†áÈ¢ò',
  `ordernum` int(11) DEFAULT NULL,
  `config` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='È°µÁ≠æÈÖçÁΩÆ\\n@author zhaozhiwei';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_uitab`
--

LOCK TABLES `sys_uitab` WRITE;
/*!40000 ALTER TABLE `sys_uitab` DISABLE KEYS */;
INSERT INTO `sys_uitab` VALUES (11,0,'willaudit','ÂæÖÂäû',1,'{}'),(12,0,'audited','Â∑≤Âäû',2,'{}');
/*!40000 ALTER TABLE `sys_uitab` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_uitable`
--

DROP TABLE IF EXISTS `sys_uitable`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_uitable` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `menuid` bigint(20) DEFAULT NULL,
  `code` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `ordernum` int(11) DEFAULT NULL,
  `issource` bit(1) DEFAULT NULL,
  `isedit` bit(1) DEFAULT NULL,
  `requirement` bit(1) DEFAULT NULL,
  `type` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `config` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_uitable`
--

LOCK TABLES `sys_uitable` WRITE;
/*!40000 ALTER TABLE `sys_uitable` DISABLE KEYS */;
INSERT INTO `sys_uitable` VALUES (11,0,'name','ÂßìÂêç',1,'\0','\0','','input','{}'),(12,0,'age','Âπ¥ÈæÑ',2,'\0','','\0','input','{}'),(13,0,'sex','ÊÄßÂà´',3,'\0','','','input','{}'),(14,6,'type','ËØ∑ÂÅáÁ±ªÂûã',1,'','\0','\0','input','{}'),(15,6,'startTime','ÂºÄÂßãÊó∂Èó¥',2,'\0','\0','\0','date','{}'),(16,6,'endTime','ÁªìÊùüÊó∂Èó¥',3,'\0','\0','\0','date','{}'),(17,6,'leavePerson','ËØ∑ÂÅá‰∫∫',4,'\0','\0','\0','input','{}'),(18,6,'wfstatus','Áä∂ÊÄÅ',5,'\0','\0','\0','input','{}');
/*!40000 ALTER TABLE `sys_uitable` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_uitoolbutton`
--

DROP TABLE IF EXISTS `sys_uitoolbutton`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_uitoolbutton` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `menuid` bigint(20) DEFAULT NULL,
  `code` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `ordernum` int(11) DEFAULT NULL,
  `action` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `config` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_uitoolbutton`
--

LOCK TABLES `sys_uitoolbutton` WRITE;
/*!40000 ALTER TABLE `sys_uitoolbutton` DISABLE KEYS */;
INSERT INTO `sys_uitoolbutton` VALUES (11,0,'save','‰øùÂ≠ò',1,'save','{}'),(13,0,'delete','Âà†Èô§',2,'delete','{}'),(14,5,'save','Êèê‰∫§',1,'save','{}'),(15,6,'audit','ÂÆ°Ê†∏',1,'audit','{}'),(16,6,'auditInfo','ÂÆ°Ê†∏ÊÉÖÂÜµ',2,'auditInfo','{}');
/*!40000 ALTER TABLE `sys_uitoolbutton` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_example`
--

DROP TABLE IF EXISTS `t_example`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_example` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  `sex` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_example`
--

LOCK TABLES `t_example` WRITE;
/*!40000 ALTER TABLE `t_example` DISABLE KEYS */;
INSERT INTO `t_example` VALUES (11,'Âº†‰∏â',30,'Áî∑'),(12,'ÊùéÂõõ',99,'Áî∑'),(13,'ÊµãËØï1',11,'11'),(14,'ÊµãËØï2',11,'12'),(15,'ÊµãËØï2',11,'12'),(16,'ÊµãËØï2',11,'12'),(17,'ÊµãËØï2',11,'12'),(18,'ÊµãËØï2',11,'12'),(19,'ÊµãËØï',1,'1'),(20,'ÊµãËØï',1,'1'),(21,'ÊµãËØï',1,'1'),(22,'ÊµãËØï',1,'1'),(23,'111',111,'111'),(24,'111',111,'111'),(25,'111',111,'111'),(32,'ÊµãËØï1',11,'11'),(33,'ÊµãËØï1',11,'11'),(34,'ÊµãËØï1',11,'11'),(35,'ÊµãËØï1',11,'11');
/*!40000 ALTER TABLE `t_example` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_leaveslip`
--

DROP TABLE IF EXISTS `t_leaveslip`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_leaveslip` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `type` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `start_time` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `end_time` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `reason` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `file` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `superior` bigint(20) DEFAULT NULL,
  `wfstatus` varchar(10) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `leave_person` varchar(30) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_leaveslip`
--

LOCK TABLES `t_leaveslip` WRITE;
/*!40000 ALTER TABLE `t_leaveslip` DISABLE KEYS */;
INSERT INTO `t_leaveslip` VALUES (6,'11','2022-05-01','2022-05-01','ÊµãËØïËØ∑ÂÅá','/tmp/11.pdf',1,'011',NULL),(7,'12','2022-05-01','2022-05-03','ËØ∑‰∏™‰∫ãÂÅá','/tmp/11.pdf',1,'011','admin'),(8,'13','2022-05-01','2022-05-08','ÊµãËØïÂ©öÂÅá','11',1,'011','admin'),(9,'12','2022-05-01','2022-05-08','ÊµãËØï‰∫ãÂÅá','11',2,'011','admin');
/*!40000 ALTER TABLE `t_leaveslip` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-06-02 13:35:37
