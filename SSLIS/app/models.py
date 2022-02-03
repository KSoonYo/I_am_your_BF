from django.db import models
import json
from django.db import models


class Conference(models.Model):
    conference_serial = models.BigAutoField(primary_key=True)
    description = models.CharField(max_length=255, blank=True, null=True)
    end_time = models.DateTimeField(blank=True, null=True)
    is_active = models.TextField(blank=True, null=True)  # This field type is a guess.
    password = models.CharField(max_length=255, blank=True, null=True)
    start_time = models.DateTimeField(blank=True, null=True)
    thumbnail = models.CharField(max_length=255, blank=True, null=True)
    title = models.CharField(max_length=255, blank=True, null=True)
    user_id = models.CharField(max_length=255, blank=True, null=True)

    class Meta:
        managed = False
        db_table = 'conference'


class ConferenceCategory(models.Model):
    id = models.BigAutoField(primary_key=True)
    name = models.CharField(max_length=255, blank=True, null=True)

    class Meta:
        managed = False
        db_table = 'conference_category'


class ConferenceHistory(models.Model):
    histroy_serial = models.BigAutoField(primary_key=True)
    enter_time = models.DateTimeField(blank=True, null=True)
    exit_time = models.DateTimeField(blank=True, null=True)
    conference_serial = models.ForeignKey(Conference, models.DO_NOTHING, db_column='conference_serial', blank=True, null=True)
    user_serial = models.ForeignKey('User', models.DO_NOTHING, db_column='user_serial', blank=True, null=True)

    class Meta:
        managed = False
        db_table = 'conference_history'


class User(models.Model):
    user_serial = models.BigAutoField(primary_key=True)
    assign = models.CharField(max_length=255, blank=True, null=True)
    create_date = models.DateTimeField(blank=True, null=True)
    description = models.CharField(max_length=255, blank=True, null=True)
    password = models.CharField(max_length=255, blank=True, null=True)
    report = models.IntegerField()
    thumbnail = models.CharField(max_length=255, blank=True, null=True)
    update_date = models.DateTimeField(blank=True, null=True)
    user_email = models.CharField(max_length=255, blank=True, null=True)
    user_id = models.CharField(max_length=255, blank=True, null=True)
    user_name = models.CharField(max_length=255, blank=True, null=True)

    class Meta:
        managed = False
        db_table = 'user'
# Create your models here.

# 기본 단어 및 수어 영상이 담긴 Model
class Basic(models.Model):
    location = models.CharField(default='' , max_length=100)
    ref_word = models.CharField(null=True, max_length=20)
    mean = models.TextField(null=True)
    part = models.CharField(max_length=10, default='')
    word = models.CharField(max_length=20, null=True, blank=True)

    def set_ref_word(self, x):
        self.ref_word = json.dumps(x)

    def get_ref_word(self):
        return json.loads(self.ref_word)

    def __str__(self):
        return self.word

# 숫자 단어 및 수어 영상이 담긴 Model
class Number(models.Model):
    location = models.CharField(default='', max_length=100)
    ref_word = models.TextField(null=True)
    mean = models.TextField(null=True)
    part = models.CharField(max_length=10, default='')
    word = models.CharField(max_length=20, null=True, blank=True)

    def __str__(self):
        return self.word

# 지화 단어 및 수어 영상이 담긴 Model
class Finger(models.Model):
    location = models.CharField(default='', max_length=100)
    ref_word = models.TextField(null=True)
    mean = models.TextField(null=True)
    part = models.CharField(max_length=10, default='')
    word = models.CharField(max_length=20, null=True, blank=True)

    def __str__(self):
        return self.word
