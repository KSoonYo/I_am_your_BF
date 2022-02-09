#my_settings.py
DATABASES = {
    'default': {
        'ENGINE': 'django.db.backends.mysql', #1
        'NAME': 'ssafy_web_db', #2
        'USER': 'ssafy', #3
        'PASSWORD': 'ssafy',  #4
        'HOST': 'localhost',   #5
        'PORT': '3306', #6
    }
}
SECRET_KEY ='django-insecure-^wwvw1%x%)rdaz!r66ms-xa5o+$m06jh0^_thb%_l9xrj$$k*b'