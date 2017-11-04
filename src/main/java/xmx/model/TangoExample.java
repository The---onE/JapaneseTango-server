package xmx.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TangoExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TangoExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andWritingIsNull() {
            addCriterion("writing is null");
            return (Criteria) this;
        }

        public Criteria andWritingIsNotNull() {
            addCriterion("writing is not null");
            return (Criteria) this;
        }

        public Criteria andWritingEqualTo(String value) {
            addCriterion("writing =", value, "writing");
            return (Criteria) this;
        }

        public Criteria andWritingNotEqualTo(String value) {
            addCriterion("writing <>", value, "writing");
            return (Criteria) this;
        }

        public Criteria andWritingGreaterThan(String value) {
            addCriterion("writing >", value, "writing");
            return (Criteria) this;
        }

        public Criteria andWritingGreaterThanOrEqualTo(String value) {
            addCriterion("writing >=", value, "writing");
            return (Criteria) this;
        }

        public Criteria andWritingLessThan(String value) {
            addCriterion("writing <", value, "writing");
            return (Criteria) this;
        }

        public Criteria andWritingLessThanOrEqualTo(String value) {
            addCriterion("writing <=", value, "writing");
            return (Criteria) this;
        }

        public Criteria andWritingLike(String value) {
            addCriterion("writing like", value, "writing");
            return (Criteria) this;
        }

        public Criteria andWritingNotLike(String value) {
            addCriterion("writing not like", value, "writing");
            return (Criteria) this;
        }

        public Criteria andWritingIn(List<String> values) {
            addCriterion("writing in", values, "writing");
            return (Criteria) this;
        }

        public Criteria andWritingNotIn(List<String> values) {
            addCriterion("writing not in", values, "writing");
            return (Criteria) this;
        }

        public Criteria andWritingBetween(String value1, String value2) {
            addCriterion("writing between", value1, value2, "writing");
            return (Criteria) this;
        }

        public Criteria andWritingNotBetween(String value1, String value2) {
            addCriterion("writing not between", value1, value2, "writing");
            return (Criteria) this;
        }

        public Criteria andPronunciationIsNull() {
            addCriterion("pronunciation is null");
            return (Criteria) this;
        }

        public Criteria andPronunciationIsNotNull() {
            addCriterion("pronunciation is not null");
            return (Criteria) this;
        }

        public Criteria andPronunciationEqualTo(String value) {
            addCriterion("pronunciation =", value, "pronunciation");
            return (Criteria) this;
        }

        public Criteria andPronunciationNotEqualTo(String value) {
            addCriterion("pronunciation <>", value, "pronunciation");
            return (Criteria) this;
        }

        public Criteria andPronunciationGreaterThan(String value) {
            addCriterion("pronunciation >", value, "pronunciation");
            return (Criteria) this;
        }

        public Criteria andPronunciationGreaterThanOrEqualTo(String value) {
            addCriterion("pronunciation >=", value, "pronunciation");
            return (Criteria) this;
        }

        public Criteria andPronunciationLessThan(String value) {
            addCriterion("pronunciation <", value, "pronunciation");
            return (Criteria) this;
        }

        public Criteria andPronunciationLessThanOrEqualTo(String value) {
            addCriterion("pronunciation <=", value, "pronunciation");
            return (Criteria) this;
        }

        public Criteria andPronunciationLike(String value) {
            addCriterion("pronunciation like", value, "pronunciation");
            return (Criteria) this;
        }

        public Criteria andPronunciationNotLike(String value) {
            addCriterion("pronunciation not like", value, "pronunciation");
            return (Criteria) this;
        }

        public Criteria andPronunciationIn(List<String> values) {
            addCriterion("pronunciation in", values, "pronunciation");
            return (Criteria) this;
        }

        public Criteria andPronunciationNotIn(List<String> values) {
            addCriterion("pronunciation not in", values, "pronunciation");
            return (Criteria) this;
        }

        public Criteria andPronunciationBetween(String value1, String value2) {
            addCriterion("pronunciation between", value1, value2, "pronunciation");
            return (Criteria) this;
        }

        public Criteria andPronunciationNotBetween(String value1, String value2) {
            addCriterion("pronunciation not between", value1, value2, "pronunciation");
            return (Criteria) this;
        }

        public Criteria andMeaningIsNull() {
            addCriterion("meaning is null");
            return (Criteria) this;
        }

        public Criteria andMeaningIsNotNull() {
            addCriterion("meaning is not null");
            return (Criteria) this;
        }

        public Criteria andMeaningEqualTo(String value) {
            addCriterion("meaning =", value, "meaning");
            return (Criteria) this;
        }

        public Criteria andMeaningNotEqualTo(String value) {
            addCriterion("meaning <>", value, "meaning");
            return (Criteria) this;
        }

        public Criteria andMeaningGreaterThan(String value) {
            addCriterion("meaning >", value, "meaning");
            return (Criteria) this;
        }

        public Criteria andMeaningGreaterThanOrEqualTo(String value) {
            addCriterion("meaning >=", value, "meaning");
            return (Criteria) this;
        }

        public Criteria andMeaningLessThan(String value) {
            addCriterion("meaning <", value, "meaning");
            return (Criteria) this;
        }

        public Criteria andMeaningLessThanOrEqualTo(String value) {
            addCriterion("meaning <=", value, "meaning");
            return (Criteria) this;
        }

        public Criteria andMeaningLike(String value) {
            addCriterion("meaning like", value, "meaning");
            return (Criteria) this;
        }

        public Criteria andMeaningNotLike(String value) {
            addCriterion("meaning not like", value, "meaning");
            return (Criteria) this;
        }

        public Criteria andMeaningIn(List<String> values) {
            addCriterion("meaning in", values, "meaning");
            return (Criteria) this;
        }

        public Criteria andMeaningNotIn(List<String> values) {
            addCriterion("meaning not in", values, "meaning");
            return (Criteria) this;
        }

        public Criteria andMeaningBetween(String value1, String value2) {
            addCriterion("meaning between", value1, value2, "meaning");
            return (Criteria) this;
        }

        public Criteria andMeaningNotBetween(String value1, String value2) {
            addCriterion("meaning not between", value1, value2, "meaning");
            return (Criteria) this;
        }

        public Criteria andToneIsNull() {
            addCriterion("tone is null");
            return (Criteria) this;
        }

        public Criteria andToneIsNotNull() {
            addCriterion("tone is not null");
            return (Criteria) this;
        }

        public Criteria andToneEqualTo(Integer value) {
            addCriterion("tone =", value, "tone");
            return (Criteria) this;
        }

        public Criteria andToneNotEqualTo(Integer value) {
            addCriterion("tone <>", value, "tone");
            return (Criteria) this;
        }

        public Criteria andToneGreaterThan(Integer value) {
            addCriterion("tone >", value, "tone");
            return (Criteria) this;
        }

        public Criteria andToneGreaterThanOrEqualTo(Integer value) {
            addCriterion("tone >=", value, "tone");
            return (Criteria) this;
        }

        public Criteria andToneLessThan(Integer value) {
            addCriterion("tone <", value, "tone");
            return (Criteria) this;
        }

        public Criteria andToneLessThanOrEqualTo(Integer value) {
            addCriterion("tone <=", value, "tone");
            return (Criteria) this;
        }

        public Criteria andToneIn(List<Integer> values) {
            addCriterion("tone in", values, "tone");
            return (Criteria) this;
        }

        public Criteria andToneNotIn(List<Integer> values) {
            addCriterion("tone not in", values, "tone");
            return (Criteria) this;
        }

        public Criteria andToneBetween(Integer value1, Integer value2) {
            addCriterion("tone between", value1, value2, "tone");
            return (Criteria) this;
        }

        public Criteria andToneNotBetween(Integer value1, Integer value2) {
            addCriterion("tone not between", value1, value2, "tone");
            return (Criteria) this;
        }

        public Criteria andPartOfSpeechIsNull() {
            addCriterion("part_of_speech is null");
            return (Criteria) this;
        }

        public Criteria andPartOfSpeechIsNotNull() {
            addCriterion("part_of_speech is not null");
            return (Criteria) this;
        }

        public Criteria andPartOfSpeechEqualTo(String value) {
            addCriterion("part_of_speech =", value, "partOfSpeech");
            return (Criteria) this;
        }

        public Criteria andPartOfSpeechNotEqualTo(String value) {
            addCriterion("part_of_speech <>", value, "partOfSpeech");
            return (Criteria) this;
        }

        public Criteria andPartOfSpeechGreaterThan(String value) {
            addCriterion("part_of_speech >", value, "partOfSpeech");
            return (Criteria) this;
        }

        public Criteria andPartOfSpeechGreaterThanOrEqualTo(String value) {
            addCriterion("part_of_speech >=", value, "partOfSpeech");
            return (Criteria) this;
        }

        public Criteria andPartOfSpeechLessThan(String value) {
            addCriterion("part_of_speech <", value, "partOfSpeech");
            return (Criteria) this;
        }

        public Criteria andPartOfSpeechLessThanOrEqualTo(String value) {
            addCriterion("part_of_speech <=", value, "partOfSpeech");
            return (Criteria) this;
        }

        public Criteria andPartOfSpeechLike(String value) {
            addCriterion("part_of_speech like", value, "partOfSpeech");
            return (Criteria) this;
        }

        public Criteria andPartOfSpeechNotLike(String value) {
            addCriterion("part_of_speech not like", value, "partOfSpeech");
            return (Criteria) this;
        }

        public Criteria andPartOfSpeechIn(List<String> values) {
            addCriterion("part_of_speech in", values, "partOfSpeech");
            return (Criteria) this;
        }

        public Criteria andPartOfSpeechNotIn(List<String> values) {
            addCriterion("part_of_speech not in", values, "partOfSpeech");
            return (Criteria) this;
        }

        public Criteria andPartOfSpeechBetween(String value1, String value2) {
            addCriterion("part_of_speech between", value1, value2, "partOfSpeech");
            return (Criteria) this;
        }

        public Criteria andPartOfSpeechNotBetween(String value1, String value2) {
            addCriterion("part_of_speech not between", value1, value2, "partOfSpeech");
            return (Criteria) this;
        }

        public Criteria andImageIsNull() {
            addCriterion("image is null");
            return (Criteria) this;
        }

        public Criteria andImageIsNotNull() {
            addCriterion("image is not null");
            return (Criteria) this;
        }

        public Criteria andImageEqualTo(String value) {
            addCriterion("image =", value, "image");
            return (Criteria) this;
        }

        public Criteria andImageNotEqualTo(String value) {
            addCriterion("image <>", value, "image");
            return (Criteria) this;
        }

        public Criteria andImageGreaterThan(String value) {
            addCriterion("image >", value, "image");
            return (Criteria) this;
        }

        public Criteria andImageGreaterThanOrEqualTo(String value) {
            addCriterion("image >=", value, "image");
            return (Criteria) this;
        }

        public Criteria andImageLessThan(String value) {
            addCriterion("image <", value, "image");
            return (Criteria) this;
        }

        public Criteria andImageLessThanOrEqualTo(String value) {
            addCriterion("image <=", value, "image");
            return (Criteria) this;
        }

        public Criteria andImageLike(String value) {
            addCriterion("image like", value, "image");
            return (Criteria) this;
        }

        public Criteria andImageNotLike(String value) {
            addCriterion("image not like", value, "image");
            return (Criteria) this;
        }

        public Criteria andImageIn(List<String> values) {
            addCriterion("image in", values, "image");
            return (Criteria) this;
        }

        public Criteria andImageNotIn(List<String> values) {
            addCriterion("image not in", values, "image");
            return (Criteria) this;
        }

        public Criteria andImageBetween(String value1, String value2) {
            addCriterion("image between", value1, value2, "image");
            return (Criteria) this;
        }

        public Criteria andImageNotBetween(String value1, String value2) {
            addCriterion("image not between", value1, value2, "image");
            return (Criteria) this;
        }

        public Criteria andVoiceIsNull() {
            addCriterion("voice is null");
            return (Criteria) this;
        }

        public Criteria andVoiceIsNotNull() {
            addCriterion("voice is not null");
            return (Criteria) this;
        }

        public Criteria andVoiceEqualTo(String value) {
            addCriterion("voice =", value, "voice");
            return (Criteria) this;
        }

        public Criteria andVoiceNotEqualTo(String value) {
            addCriterion("voice <>", value, "voice");
            return (Criteria) this;
        }

        public Criteria andVoiceGreaterThan(String value) {
            addCriterion("voice >", value, "voice");
            return (Criteria) this;
        }

        public Criteria andVoiceGreaterThanOrEqualTo(String value) {
            addCriterion("voice >=", value, "voice");
            return (Criteria) this;
        }

        public Criteria andVoiceLessThan(String value) {
            addCriterion("voice <", value, "voice");
            return (Criteria) this;
        }

        public Criteria andVoiceLessThanOrEqualTo(String value) {
            addCriterion("voice <=", value, "voice");
            return (Criteria) this;
        }

        public Criteria andVoiceLike(String value) {
            addCriterion("voice like", value, "voice");
            return (Criteria) this;
        }

        public Criteria andVoiceNotLike(String value) {
            addCriterion("voice not like", value, "voice");
            return (Criteria) this;
        }

        public Criteria andVoiceIn(List<String> values) {
            addCriterion("voice in", values, "voice");
            return (Criteria) this;
        }

        public Criteria andVoiceNotIn(List<String> values) {
            addCriterion("voice not in", values, "voice");
            return (Criteria) this;
        }

        public Criteria andVoiceBetween(String value1, String value2) {
            addCriterion("voice between", value1, value2, "voice");
            return (Criteria) this;
        }

        public Criteria andVoiceNotBetween(String value1, String value2) {
            addCriterion("voice not between", value1, value2, "voice");
            return (Criteria) this;
        }

        public Criteria andScoreIsNull() {
            addCriterion("score is null");
            return (Criteria) this;
        }

        public Criteria andScoreIsNotNull() {
            addCriterion("score is not null");
            return (Criteria) this;
        }

        public Criteria andScoreEqualTo(Integer value) {
            addCriterion("score =", value, "score");
            return (Criteria) this;
        }

        public Criteria andScoreNotEqualTo(Integer value) {
            addCriterion("score <>", value, "score");
            return (Criteria) this;
        }

        public Criteria andScoreGreaterThan(Integer value) {
            addCriterion("score >", value, "score");
            return (Criteria) this;
        }

        public Criteria andScoreGreaterThanOrEqualTo(Integer value) {
            addCriterion("score >=", value, "score");
            return (Criteria) this;
        }

        public Criteria andScoreLessThan(Integer value) {
            addCriterion("score <", value, "score");
            return (Criteria) this;
        }

        public Criteria andScoreLessThanOrEqualTo(Integer value) {
            addCriterion("score <=", value, "score");
            return (Criteria) this;
        }

        public Criteria andScoreIn(List<Integer> values) {
            addCriterion("score in", values, "score");
            return (Criteria) this;
        }

        public Criteria andScoreNotIn(List<Integer> values) {
            addCriterion("score not in", values, "score");
            return (Criteria) this;
        }

        public Criteria andScoreBetween(Integer value1, Integer value2) {
            addCriterion("score between", value1, value2, "score");
            return (Criteria) this;
        }

        public Criteria andScoreNotBetween(Integer value1, Integer value2) {
            addCriterion("score not between", value1, value2, "score");
            return (Criteria) this;
        }

        public Criteria andFrequencyIsNull() {
            addCriterion("frequency is null");
            return (Criteria) this;
        }

        public Criteria andFrequencyIsNotNull() {
            addCriterion("frequency is not null");
            return (Criteria) this;
        }

        public Criteria andFrequencyEqualTo(Integer value) {
            addCriterion("frequency =", value, "frequency");
            return (Criteria) this;
        }

        public Criteria andFrequencyNotEqualTo(Integer value) {
            addCriterion("frequency <>", value, "frequency");
            return (Criteria) this;
        }

        public Criteria andFrequencyGreaterThan(Integer value) {
            addCriterion("frequency >", value, "frequency");
            return (Criteria) this;
        }

        public Criteria andFrequencyGreaterThanOrEqualTo(Integer value) {
            addCriterion("frequency >=", value, "frequency");
            return (Criteria) this;
        }

        public Criteria andFrequencyLessThan(Integer value) {
            addCriterion("frequency <", value, "frequency");
            return (Criteria) this;
        }

        public Criteria andFrequencyLessThanOrEqualTo(Integer value) {
            addCriterion("frequency <=", value, "frequency");
            return (Criteria) this;
        }

        public Criteria andFrequencyIn(List<Integer> values) {
            addCriterion("frequency in", values, "frequency");
            return (Criteria) this;
        }

        public Criteria andFrequencyNotIn(List<Integer> values) {
            addCriterion("frequency not in", values, "frequency");
            return (Criteria) this;
        }

        public Criteria andFrequencyBetween(Integer value1, Integer value2) {
            addCriterion("frequency between", value1, value2, "frequency");
            return (Criteria) this;
        }

        public Criteria andFrequencyNotBetween(Integer value1, Integer value2) {
            addCriterion("frequency not between", value1, value2, "frequency");
            return (Criteria) this;
        }

        public Criteria andAddTimeIsNull() {
            addCriterion("add_Time is null");
            return (Criteria) this;
        }

        public Criteria andAddTimeIsNotNull() {
            addCriterion("add_Time is not null");
            return (Criteria) this;
        }

        public Criteria andAddTimeEqualTo(Date value) {
            addCriterion("add_Time =", value, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeNotEqualTo(Date value) {
            addCriterion("add_Time <>", value, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeGreaterThan(Date value) {
            addCriterion("add_Time >", value, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("add_Time >=", value, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeLessThan(Date value) {
            addCriterion("add_Time <", value, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeLessThanOrEqualTo(Date value) {
            addCriterion("add_Time <=", value, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeIn(List<Date> values) {
            addCriterion("add_Time in", values, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeNotIn(List<Date> values) {
            addCriterion("add_Time not in", values, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeBetween(Date value1, Date value2) {
            addCriterion("add_Time between", value1, value2, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeNotBetween(Date value1, Date value2) {
            addCriterion("add_Time not between", value1, value2, "addTime");
            return (Criteria) this;
        }

        public Criteria andLastTimeIsNull() {
            addCriterion("last_time is null");
            return (Criteria) this;
        }

        public Criteria andLastTimeIsNotNull() {
            addCriterion("last_time is not null");
            return (Criteria) this;
        }

        public Criteria andLastTimeEqualTo(Date value) {
            addCriterion("last_time =", value, "lastTime");
            return (Criteria) this;
        }

        public Criteria andLastTimeNotEqualTo(Date value) {
            addCriterion("last_time <>", value, "lastTime");
            return (Criteria) this;
        }

        public Criteria andLastTimeGreaterThan(Date value) {
            addCriterion("last_time >", value, "lastTime");
            return (Criteria) this;
        }

        public Criteria andLastTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("last_time >=", value, "lastTime");
            return (Criteria) this;
        }

        public Criteria andLastTimeLessThan(Date value) {
            addCriterion("last_time <", value, "lastTime");
            return (Criteria) this;
        }

        public Criteria andLastTimeLessThanOrEqualTo(Date value) {
            addCriterion("last_time <=", value, "lastTime");
            return (Criteria) this;
        }

        public Criteria andLastTimeIn(List<Date> values) {
            addCriterion("last_time in", values, "lastTime");
            return (Criteria) this;
        }

        public Criteria andLastTimeNotIn(List<Date> values) {
            addCriterion("last_time not in", values, "lastTime");
            return (Criteria) this;
        }

        public Criteria andLastTimeBetween(Date value1, Date value2) {
            addCriterion("last_time between", value1, value2, "lastTime");
            return (Criteria) this;
        }

        public Criteria andLastTimeNotBetween(Date value1, Date value2) {
            addCriterion("last_time not between", value1, value2, "lastTime");
            return (Criteria) this;
        }

        public Criteria andFlagsIsNull() {
            addCriterion("flags is null");
            return (Criteria) this;
        }

        public Criteria andFlagsIsNotNull() {
            addCriterion("flags is not null");
            return (Criteria) this;
        }

        public Criteria andFlagsEqualTo(String value) {
            addCriterion("flags =", value, "flags");
            return (Criteria) this;
        }

        public Criteria andFlagsNotEqualTo(String value) {
            addCriterion("flags <>", value, "flags");
            return (Criteria) this;
        }

        public Criteria andFlagsGreaterThan(String value) {
            addCriterion("flags >", value, "flags");
            return (Criteria) this;
        }

        public Criteria andFlagsGreaterThanOrEqualTo(String value) {
            addCriterion("flags >=", value, "flags");
            return (Criteria) this;
        }

        public Criteria andFlagsLessThan(String value) {
            addCriterion("flags <", value, "flags");
            return (Criteria) this;
        }

        public Criteria andFlagsLessThanOrEqualTo(String value) {
            addCriterion("flags <=", value, "flags");
            return (Criteria) this;
        }

        public Criteria andFlagsLike(String value) {
            addCriterion("flags like", value, "flags");
            return (Criteria) this;
        }

        public Criteria andFlagsNotLike(String value) {
            addCriterion("flags not like", value, "flags");
            return (Criteria) this;
        }

        public Criteria andFlagsIn(List<String> values) {
            addCriterion("flags in", values, "flags");
            return (Criteria) this;
        }

        public Criteria andFlagsNotIn(List<String> values) {
            addCriterion("flags not in", values, "flags");
            return (Criteria) this;
        }

        public Criteria andFlagsBetween(String value1, String value2) {
            addCriterion("flags between", value1, value2, "flags");
            return (Criteria) this;
        }

        public Criteria andFlagsNotBetween(String value1, String value2) {
            addCriterion("flags not between", value1, value2, "flags");
            return (Criteria) this;
        }

        public Criteria andDelFlagIsNull() {
            addCriterion("del_flag is null");
            return (Criteria) this;
        }

        public Criteria andDelFlagIsNotNull() {
            addCriterion("del_flag is not null");
            return (Criteria) this;
        }

        public Criteria andDelFlagEqualTo(Integer value) {
            addCriterion("del_flag =", value, "delFlag");
            return (Criteria) this;
        }

        public Criteria andDelFlagNotEqualTo(Integer value) {
            addCriterion("del_flag <>", value, "delFlag");
            return (Criteria) this;
        }

        public Criteria andDelFlagGreaterThan(Integer value) {
            addCriterion("del_flag >", value, "delFlag");
            return (Criteria) this;
        }

        public Criteria andDelFlagGreaterThanOrEqualTo(Integer value) {
            addCriterion("del_flag >=", value, "delFlag");
            return (Criteria) this;
        }

        public Criteria andDelFlagLessThan(Integer value) {
            addCriterion("del_flag <", value, "delFlag");
            return (Criteria) this;
        }

        public Criteria andDelFlagLessThanOrEqualTo(Integer value) {
            addCriterion("del_flag <=", value, "delFlag");
            return (Criteria) this;
        }

        public Criteria andDelFlagIn(List<Integer> values) {
            addCriterion("del_flag in", values, "delFlag");
            return (Criteria) this;
        }

        public Criteria andDelFlagNotIn(List<Integer> values) {
            addCriterion("del_flag not in", values, "delFlag");
            return (Criteria) this;
        }

        public Criteria andDelFlagBetween(Integer value1, Integer value2) {
            addCriterion("del_flag between", value1, value2, "delFlag");
            return (Criteria) this;
        }

        public Criteria andDelFlagNotBetween(Integer value1, Integer value2) {
            addCriterion("del_flag not between", value1, value2, "delFlag");
            return (Criteria) this;
        }

        public Criteria andTypeIsNull() {
            addCriterion("type is null");
            return (Criteria) this;
        }

        public Criteria andTypeIsNotNull() {
            addCriterion("type is not null");
            return (Criteria) this;
        }

        public Criteria andTypeEqualTo(String value) {
            addCriterion("type =", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotEqualTo(String value) {
            addCriterion("type <>", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThan(String value) {
            addCriterion("type >", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThanOrEqualTo(String value) {
            addCriterion("type >=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThan(String value) {
            addCriterion("type <", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThanOrEqualTo(String value) {
            addCriterion("type <=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLike(String value) {
            addCriterion("type like", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotLike(String value) {
            addCriterion("type not like", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeIn(List<String> values) {
            addCriterion("type in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotIn(List<String> values) {
            addCriterion("type not in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeBetween(String value1, String value2) {
            addCriterion("type between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotBetween(String value1, String value2) {
            addCriterion("type not between", value1, value2, "type");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}