/* 郵便番号API */
const YBBG_URL = 'https://zipcloud.ibsnet.co.jp/api/search';

// 郵便番号チェック（ハイフンなし7桁）
function isZcode(zcode) {
  return /^[0-9]{7}$/.test(zcode);
}

// 郵便番号から住所取得
function getAddrByYbbg(ybbg) {
  const zipcode = ybbg.replace(/[^\d]/g, '');
  const url = `${YBBG_URL}?zipcode=${zipcode}`;

  fetch(url)
    .then(res => res.json())
    .then(data => showAddrByYbbg(data))
    .catch(err => console.error(err));
}

// 住所反映（市区町村＋番地をまとめて1つのフィールドに表示）
function showAddrByYbbg(data) {
  if (data.results && data.results.length > 0) {
    const result = data.results[0];

    $('#tdhnm').val(result.address1);                   // 都道府県
    $('#adr1').val(result.address2 + result.address3);  // 市区町村＋番地まとめて

    $('#ybbgError').text('');
  } else {
    $('#ybbgError').text('該当する住所が存在しません。');
  }
}

// 郵便番号：フォーカス外れた瞬間に検索
$(document).on('focusout', '#ybbg', function () {
  const ybbg = $('#ybbg').val().trim();

  if (isZcode(ybbg)) {
    getAddrByYbbg(ybbg);
  } else if (ybbg !== '') {
    $('#ybbgError').text('郵便番号は7桁の数字で入力してください。');
  }
});
