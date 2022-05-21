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
INSERT INTO `databasechangelog` VALUES ('00000000000001','jhipster','config/liquibase/changelog/00000000000000_initial_schema.xml','2022-04-28 09:46:38',1,'EXECUTED','8:ee6ac84cdbeb19acaf73c39298fb40f8','createTable tableName=jhi_user; createTable tableName=jhi_authority; createTable tableName=jhi_user_authority; addPrimaryKey tableName=jhi_user_authority; addForeignKeyConstraint baseTableName=jhi_user_authority, constraintName=fk_authority_name, ...','',NULL,'4.6.1',NULL,NULL,'1110392312'),('20220428081257-1','jhipster','config/liquibase/changelog/20220428081257_added_entity_Menu.xml','2022-04-28 16:19:20',2,'EXECUTED','8:78a95988dd89eb412aa72c226b0c6925','createTable tableName=sys_menu','',NULL,'4.6.1',NULL,NULL,'1133959792'),('20220428081257-1-data','jhipster','config/liquibase/changelog/20220428081257_added_entity_Menu.xml','2022-04-28 16:19:20',3,'EXECUTED','8:01a6ff240d4bb49283d3de14ba345521','loadData tableName=sys_menu','',NULL,'4.6.1','faker',NULL,'1133959792'),('20220503083341-1','jhipster','config/liquibase/changelog/20220503083341_added_entity_UiComponent.xml','2022-05-03 16:57:04',4,'EXECUTED','8:d1a8ed54189536ab5e60623a2f7133b4','createTable tableName=sys_uicomponent','',NULL,'4.6.1',NULL,NULL,'1568224373'),('20220503083341-1-data','jhipster','config/liquibase/changelog/20220503083341_added_entity_UiComponent.xml','2022-05-03 16:57:05',5,'EXECUTED','8:14e127fab9099393cf8b6922a14251a4','loadData tableName=sys_uicomponent','',NULL,'4.6.1','faker',NULL,'1568224373'),('20220503083342-1','jhipster','config/liquibase/changelog/20220503083342_added_entity_UiToolButton.xml','2022-05-03 16:57:05',6,'EXECUTED','8:a00984989faaa13d01c05329e5eeb066','createTable tableName=sys_uitoolbutton','',NULL,'4.6.1',NULL,NULL,'1568224373'),('20220503083342-1-data','jhipster','config/liquibase/changelog/20220503083342_added_entity_UiToolButton.xml','2022-05-03 16:57:05',7,'EXECUTED','8:cf6d3cd718598305ac4040c85d2fb4de','loadData tableName=sys_uitoolbutton','',NULL,'4.6.1','faker',NULL,'1568224373'),('20220503083343-1','jhipster','config/liquibase/changelog/20220503083343_added_entity_UiTable.xml','2022-05-03 16:57:06',8,'EXECUTED','8:6c227879eaf083133eff7e28aaa0a7f5','createTable tableName=sys_uitable','',NULL,'4.6.1',NULL,NULL,'1568224373'),('20220503083343-1-data','jhipster','config/liquibase/changelog/20220503083343_added_entity_UiTable.xml','2022-05-03 16:57:06',9,'EXECUTED','8:65b8683246ea12a32062164d3831d2fa','loadData tableName=sys_uitable','',NULL,'4.6.1','faker',NULL,'1568224373'),('20220503083344-1','jhipster','config/liquibase/changelog/20220503083344_added_entity_UiEditform.xml','2022-05-03 16:57:06',10,'EXECUTED','8:6e35bb04e028f4b22c5ceb841f3cb7ce','createTable tableName=sys_uieditform','',NULL,'4.6.1',NULL,NULL,'1568224373'),('20220503083344-1-data','jhipster','config/liquibase/changelog/20220503083344_added_entity_UiEditform.xml','2022-05-03 16:57:06',11,'EXECUTED','8:9ce4fb7f8f075080a0e133a4c45bf027','loadData tableName=sys_uieditform','',NULL,'4.6.1','faker',NULL,'1568224373'),('20220503083345-1','jhipster','config/liquibase/changelog/20220503083345_added_entity_UiQueryform.xml','2022-05-03 16:57:07',12,'EXECUTED','8:b185356129014a88b858dcacc58cbb97','createTable tableName=sys_uiqueryform','',NULL,'4.6.1',NULL,NULL,'1568224373'),('20220503083345-1-data','jhipster','config/liquibase/changelog/20220503083345_added_entity_UiQueryform.xml','2022-05-03 16:57:07',13,'EXECUTED','8:7d9d176176c1403be896486498104cda','loadData tableName=sys_uiqueryform','',NULL,'4.6.1','faker',NULL,'1568224373'),('20220504121625-1','jhipster','config/liquibase/changelog/20220504121625_added_entity_Example.xml','2022-05-04 20:34:55',14,'EXECUTED','8:b2cbe3b600976fb47508ded908be3693','createTable tableName=t_example','',NULL,'4.6.1',NULL,NULL,'1667694945'),('20220504121625-1-data','jhipster','config/liquibase/changelog/20220504121625_added_entity_Example.xml','2022-05-04 20:34:55',15,'EXECUTED','8:69f7e341c65f756008df04426334f341','loadData tableName=t_example','',NULL,'4.6.1','faker',NULL,'1667694945'),('20220504123346-1','jhipster','config/liquibase/changelog/20220504121625_added_entity_Example.xml','2022-05-05 16:59:46',16,'EXECUTED','8:b2cbe3b600976fb47508ded908be3693','createTable tableName=t_example','',NULL,'4.6.1',NULL,NULL,'1741186154'),('20220504123346-1-data','jhipster','config/liquibase/changelog/20220504121625_added_entity_Example.xml','2022-05-05 16:59:46',17,'EXECUTED','8:69f7e341c65f756008df04426334f341','loadData tableName=t_example','',NULL,'4.6.1','faker',NULL,'1741186154'),('20220507141955-1','jhipster','config/liquibase/changelog/20220507141955_added_entity_LeaveSlip.xml','2022-05-07 22:22:27',18,'EXECUTED','8:5933d84eecf76ca243e4a975ba7397ea','createTable tableName=t_leaveslip','',NULL,'4.6.1',NULL,NULL,'1933346695'),('20220507141955-1-data','jhipster','config/liquibase/changelog/20220507141955_added_entity_LeaveSlip.xml','2022-05-07 22:22:27',19,'EXECUTED','8:c2853df03bebde01bae62dc285eacc1a','loadData tableName=t_leaveslip','',NULL,'4.6.1','faker',NULL,'1933346695'),('20220508030550-1','jhipster','config/liquibase/changelog/20220508030550_added_entity_LeaveType.xml','2022-05-08 11:10:16',20,'EXECUTED','8:b9841d88feb49a742bea4ddfef2a9d3c','createTable tableName=ele_leavetype','',NULL,'4.6.1',NULL,NULL,'1979416033'),('20220508030550-1-data','jhipster','config/liquibase/changelog/20220508030550_added_entity_LeaveType.xml','2022-05-08 11:10:16',21,'EXECUTED','8:9c60885e7adcd1f1cb40c0b7985f2f9c','loadData tableName=ele_leavetype','',NULL,'4.6.1','faker',NULL,'1979416033'),('20220510065853-1','jhipster','config/liquibase/changelog/20220510065853_added_entity_UiTab.xml','2022-05-10 15:03:00',22,'EXECUTED','8:54625e5965044bf9c5e005b4ef9e017e','createTable tableName=sys_uitab','',NULL,'4.6.1',NULL,NULL,'2166179798'),('20220510065853-1-data','jhipster','config/liquibase/changelog/20220510065853_added_entity_UiTab.xml','2022-05-10 15:03:00',23,'EXECUTED','8:638d93222d02102a1b36418cdb948295','loadData tableName=sys_uitab','',NULL,'4.6.1','faker',NULL,'2166179798'),('20220514181352-1','jhipster','config/liquibase/changelog/20220514181352_added_entity_DataPermission.xml','2022-05-15 02:16:14',24,'EXECUTED','8:47c14f534ecd78551f13f641f004da6a','createTable tableName=sys_data_permissions','',NULL,'4.6.1',NULL,NULL,'2552173977'),('20220514181352-1-data','jhipster','config/liquibase/changelog/20220514181352_added_entity_DataPermission.xml','2022-05-15 02:16:14',25,'EXECUTED','8:73f5903c34625de2687a42c74d8c35dd','loadData tableName=sys_data_permissions','',NULL,'4.6.1','faker',NULL,'2552173977'),('20220515070013-1','jhipster','config/liquibase/changelog/20220515070013_added_entity_DataPermissionDetails.xml','2022-05-15 15:01:57',26,'EXECUTED','8:39de15badf2acd6b0dafd4b97e842ee6','createTable tableName=sys_data_permission_details','',NULL,'4.6.1',NULL,NULL,'2598116848'),('20220515070013-1-data','jhipster','config/liquibase/changelog/20220515070013_added_entity_DataPermissionDetails.xml','2022-05-15 15:01:57',27,'EXECUTED','8:bb5d6ff1f0f2a34e428bdb56e3414555','loadData tableName=sys_data_permission_details','',NULL,'4.6.1','faker',NULL,'2598116848'),('20220515082952-1','jhipster','config/liquibase/changelog/20220515082952_added_entity_DataPermissionsRel.xml','2022-05-15 16:34:37',28,'EXECUTED','8:0967b6172cf96b7074fa4e36d40eac12','createTable tableName=sys_data_permissions_rel','',NULL,'4.6.1',NULL,NULL,'2603677272'),('20220515082952-1-data','jhipster','config/liquibase/changelog/20220515082952_added_entity_DataPermissionsRel.xml','2022-05-15 16:34:38',29,'EXECUTED','8:13a9128d07f5cf2fb70f01c2a4decde8','loadData tableName=sys_data_permissions_rel','',NULL,'4.6.1','faker',NULL,'2603677272'),('20220515130727-1','jhipster','config/liquibase/changelog/20220515130727_added_entity_RoleMenu.xml','2022-05-15 21:10:01',30,'EXECUTED','8:384441a05500e8c6201ac9794b03ce0d','createTable tableName=sys_role_menu','',NULL,'4.6.1',NULL,NULL,'2620201300'),('20220515130727-1-data','jhipster','config/liquibase/changelog/20220515130727_added_entity_RoleMenu.xml','2022-05-15 21:10:02',31,'EXECUTED','8:dbf7241664a36220ed4d35e0b8c82e68','loadData tableName=sys_role_menu','',NULL,'4.6.1','faker',NULL,'2620201300'),('20220515132234-1','jhipster','config/liquibase/changelog/20220515132234_added_entity_RoleMenuToolButton.xml','2022-05-15 21:24:25',32,'EXECUTED','8:3c91410f0c2c3a1897c29583c5acf242','createTable tableName=sys_role_menu_button','',NULL,'4.6.1',NULL,NULL,'2621064678'),('20220515132234-1-data','jhipster','config/liquibase/changelog/20220515132234_added_entity_RoleMenuToolButton.xml','2022-05-15 21:24:25',33,'EXECUTED','8:1de8db3ef0fd257cd180252f7f6aac89','loadData tableName=sys_role_menu_button','',NULL,'4.6.1','faker',NULL,'2621064678');
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
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_menu`
--

LOCK TABLES `sys_menu` WRITE;
/*!40000 ALTER TABLE `sys_menu` DISABLE KEYS */;
INSERT INTO `sys_menu` VALUES (1,NULL,'È¶ñÈ°µ','el-icon-s-home','','\0',0,'','{}',1,'/'),(2,NULL,'Á§∫‰æãËèúÂçï','el-icon-share','\0','',0,'','{}',2,'#'),(3,NULL,'ÊºîÁ§∫-HelloWorld','el-icon-share','\0','\0',2,'','{component:\"example/hello-world\"}',3,'/example/helloworld'),(4,NULL,'ÊºîÁ§∫-Âä®ÊÄÅÁïåÈù¢','el-icon-star-on','\0','\0',2,'','{component:\"example/uiexample\"}',4,'/example/uiexample'),(5,NULL,'ÊºîÁ§∫-ËØ∑ÂÅáÂçïÂΩïÂÖ•','el-icon-share','\0','\0',2,'','{component:\"example/leaveslip-add\"}',5,'/example/leaveslip/add'),(6,NULL,'ÊºîÁ§∫-ËØ∑ÂÅáÂçïÂÆ°Ê†∏','el-icon-share','\0','\0',2,'','{component:\"example/leaveslip-audit\"}',6,'/example/leaveslip/audit'),(7,NULL,'ËèúÂçïÁÆ°ÁêÜ','el-icon-s-data','\0','\0',0,'','{}',7,'#1'),(8,NULL,'Âü∫Á°ÄË¶ÅÁ¥†Áª¥Êä§','el-icon-s-data','\0','\0',0,'','{}',8,'#'),(9,NULL,'ËèúÂçïÁª¥Êä§',NULL,'\0','\0',7,'','{}',9,'/menu/'),(10,NULL,'ÁªÑ‰ª∂ÈÖçÁΩÆ',NULL,'\0','\0',7,'','{}',10,'/ui-component/'),(11,NULL,'ÊåâÈíÆÈÖçÁΩÆ',NULL,'\0','\0',7,'','{}',11,'/ui-tool-button/'),(12,NULL,'ÂàóË°®ÈÖçÁΩÆ',NULL,'\0','\0',7,'','{}',12,'/ui-table/'),(13,NULL,'ÁºñËæëÂå∫ÈÖçÁΩÆ',NULL,'\0','\0',7,'','{}',13,'/ui-editform/'),(14,NULL,'Êü•ËØ¢Âå∫ÈÖçÁΩÆ',NULL,'\0','\0',7,'','{}',14,'/ui-queryform/'),(15,NULL,'È°µÁ≠æÂå∫ÈÖçÁΩÆ',NULL,'\0','\0',7,'','{}',15,'/ui-tab/'),(16,NULL,'ËØ∑ÂÅáÁ±ªÂûãÁª¥Êä§',NULL,'\0','\0',8,'','{}',16,'/leave-type/'),(17,NULL,'ÊùÉÈôêÁÆ°ÁêÜ','el-icon-user-solid','\0','\0',0,'','{}',17,'#'),(18,NULL,'Êï∞ÊçÆÊùÉÈôêÁª¥Êä§',NULL,'\0','\0',17,'','{}',18,'/data-permission/'),(19,NULL,'Êï∞ÊçÆÊùÉÈôêÂàÜÈÖç',NULL,'\0','\0',17,'','{component:\"permission/data-permission\"}',19,'/sys/data-permission/create'),(20,NULL,'ÂäüËÉΩÊùÉÈôêÂàÜÈÖç',NULL,'\0','\0',17,'','{component:\"permission/tool-button-permission\"}',20,'/sys/tool-button-permission/create'),(21,NULL,'Â§ßÂ±èÁÆ°ÁêÜ','el-icon-s-platform','\0','\0',0,'','{}',21,'#'),(22,NULL,'Á≥ªÁªüÈÖçÁΩÆ','el-icon-setting','\0','\0',0,'','{}',22,'#'),(23,NULL,'Á≥ªÁªüÂèÇÊï∞ÈÖçÁΩÆ',NULL,'\0','\0',22,'','{}',23,'#'),(24,NULL,'ÂÆöÊó∂‰ªªÂä°ÈÖçÁΩÆ',NULL,'\0','\0',22,'','{}',24,'#'),(25,NULL,'‰ª™Ë°®ÁõòÁªÑ‰ª∂Áª¥Êä§',NULL,'\0','\0',21,'','{}',25,'#'),(26,NULL,'‰ª™Ë°®ÁõòÁªÑ‰ª∂ÈÖçÁΩÆ',NULL,'\0','\0',21,'','{}',26,'#'),(27,NULL,'Á≥ªÁªüÁõëÊéß','el-icon-camera-solid','\0','\0',0,'','{}',27,'#'),(28,NULL,'Âú®Á∫ø‰∫∫ÂëòÁõëÊéß',NULL,'\0','\0',27,'','{}',28,'#'),(29,NULL,'Êìç‰ΩúÊó•ÂøóÁõëÊéß',NULL,'\0','\0',27,'','{}',29,'#'),(30,NULL,'ÈááÈõÜË°®ÂÆö‰πâ',NULL,'\0','\0',22,'','{}',30,'#'),(31,NULL,'ËèúÂçïËßíËâ≤ÊùÉÈôêÂàÜÈÖç',NULL,'\0','\0',17,'','{component:\"permission/role-menu\"}',31,'/sys/role-menu/create');
/*!40000 ALTER TABLE `sys_menu` ENABLE KEYS */;
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

-- Dump completed on 2022-05-21 10:07:56
