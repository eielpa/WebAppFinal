PGDMP      %        	        }            movies    17.0    17.0 A    A           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                           false            B           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                           false            C           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                           false            D           1262    16446    movies    DATABASE     y   CREATE DATABASE movies WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'Italian_Italy.1252';
    DROP DATABASE movies;
                     postgres    false            �            1259    16455 
   categories    TABLE     ]   CREATE TABLE public.categories (
    id integer NOT NULL,
    name character varying(100)
);
    DROP TABLE public.categories;
       public         heap r       postgres    false            �            1259    16454    categories_id_seq    SEQUENCE     �   CREATE SEQUENCE public.categories_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 (   DROP SEQUENCE public.categories_id_seq;
       public               postgres    false    220            E           0    0    categories_id_seq    SEQUENCE OWNED BY     G   ALTER SEQUENCE public.categories_id_seq OWNED BY public.categories.id;
          public               postgres    false    219            �            1259    16476 	   downloads    TABLE       CREATE TABLE public.downloads (
    id integer NOT NULL,
    user_id character varying,
    movie_id character varying,
    download_date timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    download_status character varying(50) DEFAULT 'In corso'::character varying
);
    DROP TABLE public.downloads;
       public         heap r       postgres    false            �            1259    16475    downloads_id_seq    SEQUENCE     �   CREATE SEQUENCE public.downloads_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 '   DROP SEQUENCE public.downloads_id_seq;
       public               postgres    false    224            F           0    0    downloads_id_seq    SEQUENCE OWNED BY     E   ALTER SEQUENCE public.downloads_id_seq OWNED BY public.downloads.id;
          public               postgres    false    223            �            1259    16462    movies    TABLE       CREATE TABLE public.movies (
    id integer NOT NULL,
    title character varying(200),
    description text,
    release_year integer,
    category_id integer,
    rating double precision,
    added_date timestamp without time zone DEFAULT CURRENT_TIMESTAMP
);
    DROP TABLE public.movies;
       public         heap r       postgres    false            �            1259    16461    movies_id_seq    SEQUENCE     �   CREATE SEQUENCE public.movies_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 $   DROP SEQUENCE public.movies_id_seq;
       public               postgres    false    222            G           0    0    movies_id_seq    SEQUENCE OWNED BY     ?   ALTER SEQUENCE public.movies_id_seq OWNED BY public.movies.id;
          public               postgres    false    221            �            1259    16515    movies_rating_seq    SEQUENCE     x   CREATE SEQUENCE public.movies_rating_seq
    START WITH 0
    INCREMENT BY 1
    MINVALUE 0
    MAXVALUE 5
    CACHE 1;
 (   DROP SEQUENCE public.movies_rating_seq;
       public               postgres    false    222            H           0    0    movies_rating_seq    SEQUENCE OWNED BY     G   ALTER SEQUENCE public.movies_rating_seq OWNED BY public.movies.rating;
          public               postgres    false    227            �            1259    16571    payments    TABLE     $  CREATE TABLE public.payments (
    id integer NOT NULL,
    movie_id integer NOT NULL,
    user_id character varying(255) NOT NULL,
    transaction_id character varying(255) NOT NULL,
    status character varying(50),
    payment_date timestamp without time zone DEFAULT CURRENT_TIMESTAMP
);
    DROP TABLE public.payments;
       public         heap r       postgres    false            �            1259    16570    payments_id_seq    SEQUENCE     �   CREATE SEQUENCE public.payments_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 &   DROP SEQUENCE public.payments_id_seq;
       public               postgres    false    229            I           0    0    payments_id_seq    SEQUENCE OWNED BY     C   ALTER SEQUENCE public.payments_id_seq OWNED BY public.payments.id;
          public               postgres    false    228            �            1259    16495    personal_library    TABLE     '  CREATE TABLE public.personal_library (
    id integer NOT NULL,
    user_id character varying(255),
    movie_id character varying(255),
    download_date timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    download_status character varying(50) DEFAULT 'Completato'::character varying
);
 $   DROP TABLE public.personal_library;
       public         heap r       postgres    false            �            1259    16494    personal_library_id_seq    SEQUENCE     �   CREATE SEQUENCE public.personal_library_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 .   DROP SEQUENCE public.personal_library_id_seq;
       public               postgres    false    226            J           0    0    personal_library_id_seq    SEQUENCE OWNED BY     S   ALTER SEQUENCE public.personal_library_id_seq OWNED BY public.personal_library.id;
          public               postgres    false    225            �            1259    16638    ratings    TABLE     '  CREATE TABLE public.ratings (
    id integer NOT NULL,
    movie_id character varying NOT NULL,
    user_id character varying NOT NULL,
    rating double precision NOT NULL,
    CONSTRAINT ratings_rating_check CHECK (((rating >= (0)::double precision) AND (rating <= (5)::double precision)))
);
    DROP TABLE public.ratings;
       public         heap r       postgres    false            �            1259    16637    ratings_id_seq    SEQUENCE     �   CREATE SEQUENCE public.ratings_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 %   DROP SEQUENCE public.ratings_id_seq;
       public               postgres    false    233            K           0    0    ratings_id_seq    SEQUENCE OWNED BY     A   ALTER SEQUENCE public.ratings_id_seq OWNED BY public.ratings.id;
          public               postgres    false    232            �            1259    16448    users    TABLE     �   CREATE TABLE public.users (
    id character varying NOT NULL,
    name character varying(100),
    email character varying(100),
    password character varying(100),
    dob date
);
    DROP TABLE public.users;
       public         heap r       postgres    false            �            1259    16447    users_id_seq    SEQUENCE     �   CREATE SEQUENCE public.users_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 #   DROP SEQUENCE public.users_id_seq;
       public               postgres    false    218            L           0    0    users_id_seq    SEQUENCE OWNED BY     =   ALTER SEQUENCE public.users_id_seq OWNED BY public.users.id;
          public               postgres    false    217            �            1259    16582    wishlist    TABLE     �   CREATE TABLE public.wishlist (
    id bigint NOT NULL,
    user_id character varying(255) NOT NULL,
    movie_id character varying(255) NOT NULL,
    movie_title character varying(255)
);
    DROP TABLE public.wishlist;
       public         heap r       postgres    false            �            1259    16581    wishlist_id_seq    SEQUENCE     �   CREATE SEQUENCE public.wishlist_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 &   DROP SEQUENCE public.wishlist_id_seq;
       public               postgres    false    231            M           0    0    wishlist_id_seq    SEQUENCE OWNED BY     C   ALTER SEQUENCE public.wishlist_id_seq OWNED BY public.wishlist.id;
          public               postgres    false    230            |           2604    16458    categories id    DEFAULT     n   ALTER TABLE ONLY public.categories ALTER COLUMN id SET DEFAULT nextval('public.categories_id_seq'::regclass);
 <   ALTER TABLE public.categories ALTER COLUMN id DROP DEFAULT;
       public               postgres    false    219    220    220            �           2604    16479    downloads id    DEFAULT     l   ALTER TABLE ONLY public.downloads ALTER COLUMN id SET DEFAULT nextval('public.downloads_id_seq'::regclass);
 ;   ALTER TABLE public.downloads ALTER COLUMN id DROP DEFAULT;
       public               postgres    false    223    224    224            }           2604    16465 	   movies id    DEFAULT     f   ALTER TABLE ONLY public.movies ALTER COLUMN id SET DEFAULT nextval('public.movies_id_seq'::regclass);
 8   ALTER TABLE public.movies ALTER COLUMN id DROP DEFAULT;
       public               postgres    false    221    222    222            ~           2604    16524    movies rating    DEFAULT     n   ALTER TABLE ONLY public.movies ALTER COLUMN rating SET DEFAULT nextval('public.movies_rating_seq'::regclass);
 <   ALTER TABLE public.movies ALTER COLUMN rating DROP DEFAULT;
       public               postgres    false    227    222            �           2604    16574    payments id    DEFAULT     j   ALTER TABLE ONLY public.payments ALTER COLUMN id SET DEFAULT nextval('public.payments_id_seq'::regclass);
 :   ALTER TABLE public.payments ALTER COLUMN id DROP DEFAULT;
       public               postgres    false    228    229    229            �           2604    16498    personal_library id    DEFAULT     z   ALTER TABLE ONLY public.personal_library ALTER COLUMN id SET DEFAULT nextval('public.personal_library_id_seq'::regclass);
 B   ALTER TABLE public.personal_library ALTER COLUMN id DROP DEFAULT;
       public               postgres    false    225    226    226            �           2604    16641 
   ratings id    DEFAULT     h   ALTER TABLE ONLY public.ratings ALTER COLUMN id SET DEFAULT nextval('public.ratings_id_seq'::regclass);
 9   ALTER TABLE public.ratings ALTER COLUMN id DROP DEFAULT;
       public               postgres    false    232    233    233            {           2604    16551    users id    DEFAULT     d   ALTER TABLE ONLY public.users ALTER COLUMN id SET DEFAULT nextval('public.users_id_seq'::regclass);
 7   ALTER TABLE public.users ALTER COLUMN id DROP DEFAULT;
       public               postgres    false    218    217    218            �           2604    16588    wishlist id    DEFAULT     j   ALTER TABLE ONLY public.wishlist ALTER COLUMN id SET DEFAULT nextval('public.wishlist_id_seq'::regclass);
 :   ALTER TABLE public.wishlist ALTER COLUMN id DROP DEFAULT;
       public               postgres    false    230    231    231            1          0    16455 
   categories 
   TABLE DATA           .   COPY public.categories (id, name) FROM stdin;
    public               postgres    false    220   �H       5          0    16476 	   downloads 
   TABLE DATA           Z   COPY public.downloads (id, user_id, movie_id, download_date, download_status) FROM stdin;
    public               postgres    false    224   |I       3          0    16462    movies 
   TABLE DATA           g   COPY public.movies (id, title, description, release_year, category_id, rating, added_date) FROM stdin;
    public               postgres    false    222   �I       :          0    16571    payments 
   TABLE DATA           _   COPY public.payments (id, movie_id, user_id, transaction_id, status, payment_date) FROM stdin;
    public               postgres    false    229   9Z       7          0    16495    personal_library 
   TABLE DATA           a   COPY public.personal_library (id, user_id, movie_id, download_date, download_status) FROM stdin;
    public               postgres    false    226   VZ       >          0    16638    ratings 
   TABLE DATA           @   COPY public.ratings (id, movie_id, user_id, rating) FROM stdin;
    public               postgres    false    233   K[       /          0    16448    users 
   TABLE DATA           ?   COPY public.users (id, name, email, password, dob) FROM stdin;
    public               postgres    false    218   R`       <          0    16582    wishlist 
   TABLE DATA           F   COPY public.wishlist (id, user_id, movie_id, movie_title) FROM stdin;
    public               postgres    false    231   �a       N           0    0    categories_id_seq    SEQUENCE SET     @   SELECT pg_catalog.setval('public.categories_id_seq', 1, false);
          public               postgres    false    219            O           0    0    downloads_id_seq    SEQUENCE SET     ?   SELECT pg_catalog.setval('public.downloads_id_seq', 1, false);
          public               postgres    false    223            P           0    0    movies_id_seq    SEQUENCE SET     =   SELECT pg_catalog.setval('public.movies_id_seq', 125, true);
          public               postgres    false    221            Q           0    0    movies_rating_seq    SEQUENCE SET     @   SELECT pg_catalog.setval('public.movies_rating_seq', 0, false);
          public               postgres    false    227            R           0    0    payments_id_seq    SEQUENCE SET     >   SELECT pg_catalog.setval('public.payments_id_seq', 1, false);
          public               postgres    false    228            S           0    0    personal_library_id_seq    SEQUENCE SET     F   SELECT pg_catalog.setval('public.personal_library_id_seq', 18, true);
          public               postgres    false    225            T           0    0    ratings_id_seq    SEQUENCE SET     >   SELECT pg_catalog.setval('public.ratings_id_seq', 112, true);
          public               postgres    false    232            U           0    0    users_id_seq    SEQUENCE SET     ;   SELECT pg_catalog.setval('public.users_id_seq', 1, false);
          public               postgres    false    217            V           0    0    wishlist_id_seq    SEQUENCE SET     >   SELECT pg_catalog.setval('public.wishlist_id_seq', 60, true);
          public               postgres    false    230            �           2606    16460    categories categories_pkey 
   CONSTRAINT     X   ALTER TABLE ONLY public.categories
    ADD CONSTRAINT categories_pkey PRIMARY KEY (id);
 D   ALTER TABLE ONLY public.categories DROP CONSTRAINT categories_pkey;
       public                 postgres    false    220            �           2606    16483    downloads downloads_pkey 
   CONSTRAINT     V   ALTER TABLE ONLY public.downloads
    ADD CONSTRAINT downloads_pkey PRIMARY KEY (id);
 B   ALTER TABLE ONLY public.downloads DROP CONSTRAINT downloads_pkey;
       public                 postgres    false    224            �           2606    16469    movies movies_pkey 
   CONSTRAINT     P   ALTER TABLE ONLY public.movies
    ADD CONSTRAINT movies_pkey PRIMARY KEY (id);
 <   ALTER TABLE ONLY public.movies DROP CONSTRAINT movies_pkey;
       public                 postgres    false    222            �           2606    16579    payments payments_pkey 
   CONSTRAINT     T   ALTER TABLE ONLY public.payments
    ADD CONSTRAINT payments_pkey PRIMARY KEY (id);
 @   ALTER TABLE ONLY public.payments DROP CONSTRAINT payments_pkey;
       public                 postgres    false    229            �           2606    16502 &   personal_library personal_library_pkey 
   CONSTRAINT     d   ALTER TABLE ONLY public.personal_library
    ADD CONSTRAINT personal_library_pkey PRIMARY KEY (id);
 P   ALTER TABLE ONLY public.personal_library DROP CONSTRAINT personal_library_pkey;
       public                 postgres    false    226            �           2606    16613 6   personal_library personal_library_user_id_movie_id_key 
   CONSTRAINT     ~   ALTER TABLE ONLY public.personal_library
    ADD CONSTRAINT personal_library_user_id_movie_id_key UNIQUE (user_id, movie_id);
 `   ALTER TABLE ONLY public.personal_library DROP CONSTRAINT personal_library_user_id_movie_id_key;
       public                 postgres    false    226    226            �           2606    16646    ratings ratings_pkey 
   CONSTRAINT     R   ALTER TABLE ONLY public.ratings
    ADD CONSTRAINT ratings_pkey PRIMARY KEY (id);
 >   ALTER TABLE ONLY public.ratings DROP CONSTRAINT ratings_pkey;
       public                 postgres    false    233            �           2606    16553    users users_pkey 
   CONSTRAINT     N   ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (id);
 :   ALTER TABLE ONLY public.users DROP CONSTRAINT users_pkey;
       public                 postgres    false    218            �           2606    16590    wishlist wishlist_pkey 
   CONSTRAINT     T   ALTER TABLE ONLY public.wishlist
    ADD CONSTRAINT wishlist_pkey PRIMARY KEY (id);
 @   ALTER TABLE ONLY public.wishlist DROP CONSTRAINT wishlist_pkey;
       public                 postgres    false    231            1   �   x�-���@Dk�cK�2$B4i%��Z�R���C"_�#(�x3\�Lh�Dh��^Xs�4*�S�HC��=�T�X����}��Pg���?����V�$ɥ����<���0������n8R�����1��+8������c��_��8�      5      x������ � �      3      x��Z�r�8�]�_����R�β-��RYc���l�$2%�`�d�Ҫ~�#f"f���������Ϲ ��Do�;��P����8�����M�W�¦�ue�T^��+�l�x��V��̪��F�����SZ]�Z�*W&7��d|z:K����I2M�^��/�c5>:�M�&�/OF'����$�m�W_����+����~r���+�6[p�q�ڪ��uj��s�r�4Z>�N����ˢ2���u���ъ�<����'�H^m�o�l��\i��*z�[���ԹQX]�:�hod?�����d4>>�$��6�˖�Y�=j��p!��ԅ��^;�W��8m�[����,�֔�U����K|�����J-ǝ$���)#��e�����q��ڭJ�~��I��i�-*����m�S���� c���T�+�#F���S6̒O�ѥMa�8�sdX[ۘ
�+],,�׈q�z͝l��j]3�����H��q���㧶=����N����u���Х�˥g�q����x�>�8_W�4t����!�X����4<��D���d;N.��(��-��
�7^;���M���XU���-2?�	�Zml]�0�p�$��0�p�a�J+p:����=�[#��Ô��OYύ)����֭�jeJ���!�����7���b�s)�өd�x"!�֍�߸w8l�*��$�y���#+U�b/Ԝ*a��VҐ_��^J��t��O�۵7rZ��Jj�-N]X��&� }�k�'59)Ѫ�([���4K�Bo�!|��p@H�q2r ʅ�B
���r��|�Ht��P�[��.ӄ;�#ob�'C8�&�r�*��˖�g�{W�ܖ5�����w3s ���˅¡GRO�x��Wn� ��#������Z�m���c�n���	��p7R���Մ)	1��1惣}�>ci4��̓۲�R���Fq�c�&ԩ�aS��<��F�Q7��2��V���"97:�!@����K"E���4�T�6��]m�=���=��%�67`�.FW��Z𼋒^z�_�%�1��J�6��U���;���o�DI�el�C,�Y@��GfI�1oHg�yc$e2A9�[İ�.������Z__�����^���Z�;���N�Spo��0UB������j�n����IS�nc�%>~�=P��(�;�$��;oL��:�A�jb~�n��#$�<�	��DT�V��mq�w:h����Q൩�1�N@h���@}��kֺP�ms/n�H��;h��������\�A���!̇}�C킕��DƇ��� ����9����B�a2M.����v�[��-�O�|�C�$�+(I-���[��A;��3x:�SX��{�"���T�-� �c�2؀S��*&uL�\pҒˈ'G��OK� �o��~��{�ၦ�0�rum��y'��L�:�`��֪��~�󠄧OG�8y���Kq��o���OF�)�?4"�����H3GΗ�E�������%*���j��!�МL~�-Z]JD��4_4��V#ǔL�P�:5�����-rP��P �@H�6��AM2	h���K��H u��f����\���D�S�MP}��d�d&�w�uw�����ج��d�i�AH0��=���*�}��֞��T�C�}]�! �xi3׿/a���)��a�a^M���6[1E z��5�_8�
��&�Ժ+!1���^��WF�}�m<t�>��t� ����LWlH~9;��A]W� �U�"�!�k~_�\�]]�4KJ2�i2T��Ir�3��΀G�^}tZX�|�#�� @�ғ��D�FX���k���\�*�]�TK��n����T�vnW�
e����=n�.M�J;A��.���M�_��/���~�z���a��p�Ngɛ_t�r�s���-M�N�����8��pЧ&j�[ԅa�!���zP|�	vH��l
`�����[i�?H��9`EUS3L�Fa�莅�;�=�A��yd�rl���bI�_[��-^ࠒ��!�j��\�v������]k����. �����E��"dü�)q$G��ؚƶP�Pt��������Ӧ̓W.�#���j�&[��?��	����B9P54:��
=r�aN��������?~�����u9����x�.�'ɵ��{�j�G:*���ZD�3x���K{T	qK�� D�0��0����t���R��i����%Ȫ�+�6����
w��AeF[,aUX�d#��*��~��2�t���y�;FB�82%�O�����������^m����W�E�"|��Ͷ�2����&0i7D�e�v
Y�'����(se�9�+���kB��Q$Hh!���v'���l���XA}l��LF�S���������G̰~�}:f�0��	y؍Rb���Z�`�9%g���?;jx��(��8i���AH_v�ei������yA*c5`�QN 1�a���%	$>�%Ǐ?>�Φ'/'G��l�l6K�`�+����3'���EE���z��vR�w�L��)�!�M�g��Q����{�L������c5J��NL@2��$b2��|�f�<x��\v�x��q_���>z��\@/l�U��#�;��8�rL�C����^���ɓ�<�Nr�s�/��|]��+,�%��]��ӡ�Y���I������P�Y��n�7��O����sB͵��hb�nl��+���Mf�
�����U��ڎ���8yl 0ҥ�pwD4�Ԁ�M�{����L �h0N�˒�ރQk�g_���*��w��|�"D]�0���t CtZ���r��9P��q2~,�->e�ot�	%72�����)��\֐]�,�����:�6�A(a�[��@��no�H���Q2x��)'O|4b"ȿR�#�")Z^*�2���J�P�uX�CF�v���K�p�:�J�����d�xPth�8��
���C8!1� �L72�d��T��3eC�u
x���q�^ދW_��~�0���bO��ʭMK�ǵ�j@�\%Z��r[�!go����������s9��$��] 2��k8��r�����-�2�7�?!Z��6���#�0��Z���.)c�I�`��B}p�$�؂gE���F�����h�ܮ[6g�Ιwcˠ��֌wL�n�o+�}�
�Kx(�K���~vW��m��D�YO������x���=��'�JIޠ=�_X�hn��=u^�Uo%è:j\rQj�8YdB.��{
�5���ww�CpstLQ��-m��!=���c�l' r��� W��V�}$V����!E�,P�px����@P/�%i��ݴf4��9��O�AN�o�x=��ֹᄝ]�5�lGt#���)-�:��j.�+�p7����$V�a�n��9��R�픤`����k��������H�ʹ<4�/�ۙފ0^� SJ�rm-���3�<n�p���9A�֝�-����RS2��p��n���!?%_ֶ�uHԮbF���~���tx����<[��K}:��G(Pf���[�������a��]�ǰ������8ք���;ς�����"3�����:J�Wy祫8����H�%�`�e�Q��śO��n��͇D��9WW]�v���*Mѕ|��	��W6�Gln9�r!�W<dMV�y�!��搩W�Мi�X[�4ȯ��c��mE�	������h������2�5H��^���f��oD����a��l��Sy'|���;r&4��(u��xH,�:>�W�]{i=!LOA�����\���A�y��ԧ��Z�>~�O���խ�d�/hɐ��p�}_Ȇ��tl[�a�S�"q�5�~��/�N�Ϸ�7�_���p�������ŵ.�G�Y&�#R���X���	c��RL��A��d¾��|)��l$Ioz�8X@�Q��Ъ�B�l���o��=4)ʠ��W_l��ю�_L�<Vl�O�z�n쟏R"�D�=`�]�χ:�y��~��_Z�Gf(�y��'F��Y�Q��ޭc�vRuDm�Ѓ�VG �   ��+�y�2��9�Q�k��O�
�F�}+�$�{U����	�ȳ-Y����Ҡ��@�lt�DD��,��� �.^酅��<����b��.3�O��
]F/L�ne���,);��M��r��Ʉ�����Ϟ=�'���/      :      x������ � �      7   �   x�]ѹj1�z�)�<̩c���`q�@�4�q��`��X)䬶����HC�.��e#�y�61;G�Qن����r|����@�9�fY�'l�� ;0#R�5!ۦ$:P�V�ք!�'a����iF�<&�5!�]9G�*��mDuC��D�>�y?�����������r�M�CW���K��)Z��1o��[1�S�U�Z.�Ot+�g2rA����O��Uw�      >   �  x��V�r9}��B? 5�ht�-��k6W�e_��b��GF3c6|��?�-��ŉU�r��iM��E��������wKo�@G_��u�y�l-)'�I,V<���%����ҭm{C/��m�������9z�x�tMc陿r�����b�2��E��UL'g���X{���}� �;J5a%9�_� �H��YC�앏��:ρ'~���U��e6��;�/*Ӈ�AH��z�P���~��U�*�Ԅ���O������H?��^�y�\�g���x�(E�9�3r�v��m�����Rf��Q$9E�K�������m���o��!���w.v#��\<�ɳ,�J���{Kn�.=�h�Rp����%��˧�F���^R7Ȝrn$ym�ttj��j<@�ϕ�8�*&%#g�.>��d�J~�΋���$S(�Y[G���7ѤY)c�Ry�;2'�^)c��"�n�W3V_���c5d:ؘ:��ȩml�y�4��Oh:)���[ �h6���Ʋ�#K����6��,$�=3�ы��З�����v�p���#��CUJ���ƻ����:���>��N�������B帪�]�*F��1��y�S�:
�ͽ룥�}�pm�I��?�/P�
�t=d�k�xa�*V� �s���@��C�b��<�f�`�rV�8k�R�eX��X�m��3"�Y�c(�Y=��d"v�|���D��,M��ww�$�������z�����c�!D��L���͓������b�W,k#ů{r�fu�39���<<�kh�qtO�/�o:c��:�>�����sҦ���&t�0���%�����ٮ�:��o�Y#��e��i�D�p�*���f�2��¯� ݻt+��'-�����Ӱ��U�7��A�Y�����zc^U��?�� �B�U�%��_���B��>�i��S3*��nإʐ��la����$U�tq �ɧy�����a�E(������iu���))Z�PiK,��ъ�J�}��E�9��~,����{F����k�׶_�KUj�CV�a��������i�Ά?N�8�M�i�l���~�w�$��1T���:O���O�,F�p���nІ����1��"���з���H�:`Eq����
��o�6��Ǚ������`EINC=n�IV�,|�zE�1�!u�?l����qK/�:�i�0}�z�Ȍ�z��2d.0V�8��
��s�M`R�0X�"X6�=x��({�X%yS0?�L&�0e%�      /   X  x�e��N�@F��St��a(T���B)�g՘���ad:��+��+�b"�01���s6'w[1��6Ʋ9@Y�5�������Pm����/�_݈�IE8F�f�>�Č/C ~\E!!�Jc!^a��z4D��3K�l�[:W��o��6���y}�	�dF����B?��Z!���t�{�Vӌ�O!E��ʧE[�����`⡫��DoE��L��ק癠�\S�+fy�FJI���������C�Gg'��zqϚ]��%/�Q�q]v�Ni���h8O��s,/�6�ܺ=���L8��YPn��8NO.��q���O�,,�X�����Z�E=<�<0�:]jG��i�_�⒭      <   u   x�5�A
�0F�ur���&�����n�fЁtFB]���B����c��!��ET0��,��¸�*�i�kn������}�W�,�̂$����t�fbA�\e'�+#�x������*�     