package com.tcsoft.searchmachinary.model.action;

import android.util.Log;

import com.tcsoft.searchmachinary.bean.Book;
import com.tcsoft.searchmachinary.bean.Consult;
import com.tcsoft.searchmachinary.bean.Holding;
import com.tcsoft.searchmachinary.bean.Search;
import com.tcsoft.searchmachinary.model.api.BookApi;
import com.tcsoft.searchmachinary.model.api.BookApiImpl;
import com.tcsoft.searchmachinary.model.asyn.AsyncTaskAction;
import com.tcsoft.searchmachinary.model.asyn.AsyncTaskListener;
import com.tcsoft.searchmachinary.model.listener.ActionListener;
import com.tcsoft.searchmachinary.utils.TimeUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class BookActionImpl implements BookAction {

    private static final String TAG = "BookActionImpl";
    private BookApi bookApi;

    public BookActionImpl() {
        bookApi = new BookApiImpl();
    }


    @Override
    public void newBook(final String page, final ActionListener<Search> listener) {
        AsyncTaskAction asyncTaskAction = new AsyncTaskAction(new AsyncTaskListener<String>() {
            @Override
            public String background() {
                return bookApi.newBook(String.valueOf(page));
            }

            @Override
            public void result(String result) {
                if (result != null) {
                    Log.d(TAG, result);
                    Search search = new Search();
                    List<Book> list = new ArrayList<>();
                    try {
                        JSONObject jsonObject = new JSONObject(result);
                        JSONArray jsonArray = jsonObject.getJSONArray("bookList");
                        for (int i = 0; i < jsonArray.length(); i++) {
                            Book book = new Book();
                            book.setTitle(jsonArray.getJSONObject(i).getString("title"));
                            book.setAuthor(jsonArray.getJSONObject(i).getString("author"));
                            try {
                                book.setCallNo(jsonArray.getJSONObject(i).getString("callno"));
                            } catch (JSONException e) {
                                book.setCallNo("");
                            }

                            book.setPublisher(jsonArray.getJSONObject(i).getString("publisher"));
                            book.setPubDate(jsonArray.getJSONObject(i).getString("pubdate"));
                            book.setRecNo(jsonArray.getJSONObject(i).getString("bookrecno"));
                            book.setIsbn(jsonArray.getJSONObject(i).getString("isbn"));
                            list.add(book);
                        }

                        search.setTotal(jsonObject.getInt("totalCount"));
                        search.setbList(list);
                        listener.onSuccess(search);

                    } catch (JSONException e) {
                        listener.onFailure(TAG, "连接异常");
                    }

                } else {
                    listener.onFailure(TAG, "连接失败");

                }
            }
        });
        asyncTaskAction.execute();

    }


    @Override
    public void hotBook(final ActionListener<Search> listener) {
        AsyncTaskAction asyncTaskAction = new AsyncTaskAction(new AsyncTaskListener<String>() {
            @Override
            public String background() {
                return bookApi.hotBook();
            }

            @Override
            public void result(String result) {
                if (result != null) {
                    Log.d(TAG, result);
                    Search search = new Search();
                    List<Book> list = new ArrayList<>();
                    try {
                        JSONArray jsonArray = new JSONArray(result);
                        Log.d(TAG, jsonArray.length() + "");
                        for (int i = 0; i < jsonArray.length(); i++) {
                            Book book = new Book();
                            book.setTitle(jsonArray.getJSONObject(i).getJSONObject("biblios").getString("title"));
                            book.setAuthor(jsonArray.getJSONObject(i).getJSONObject("biblios").getString("author"));
                            try {
                                book.setCallNo(jsonArray.getJSONObject(i).getJSONObject("biblios").getString("callno"));
                            } catch (JSONException e) {
                                book.setCallNo("");
                            }

                            book.setPublisher(jsonArray.getJSONObject(i).getJSONObject("biblios").getString("publisher"));
                            book.setPubDate(jsonArray.getJSONObject(i).getJSONObject("biblios").getString("pubdate"));
                            book.setRecNo(jsonArray.getJSONObject(i).getJSONObject("biblios").getString("bookrecno"));
                            book.setIsbn(jsonArray.getJSONObject(i).getJSONObject("biblios").getString("isbn"));
                            book.setLoanNum(jsonArray.getJSONObject(i).getInt("totalNum"));
                            list.add(book);
                        }

                        search.setTotal(99);
                        search.setbList(list);
                        listener.onSuccess(search);

                    } catch (JSONException e) {
                        listener.onFailure(TAG, "连接异常");
                    }

                } else {
                    listener.onFailure(TAG, "连接失败");

                }
            }
        });
        asyncTaskAction.execute();

    }


    @Override
    public void searchBook(final String type, final String title, final String page, boolean onlyCanLoan, final ActionListener<Search> listener) {
        AsyncTaskAction asyncTaskAction = new AsyncTaskAction(new AsyncTaskListener<String>() {
            @Override
            public String background() {
                return bookApi.searchBook(type, title, page);
            }

            @Override
            public void result(String result) {
                if (result != null) {
                    Log.d(TAG, result);
                    Search search = new Search();
                    List<Book> list = new ArrayList<>();
                    try {
                        JSONObject jsonObject = new JSONObject(result);
                        JSONArray jsonArray = jsonObject.getJSONArray("pagelist");
                        for (int i = 0; i < jsonArray.length(); i++) {
                            Book book = new Book();
                            book.setTitle(jsonArray.getJSONObject(i).getString("title"));
                            book.setAuthor(jsonArray.getJSONObject(i).getString("author"));
                            book.setCallNo(jsonArray.getJSONObject(i).getString("callno"));

                            book.setPublisher(jsonArray.getJSONObject(i).getString("publisher"));
                            book.setPubDate(jsonArray.getJSONObject(i).getString("pubdate"));
                            book.setRecNo(jsonArray.getJSONObject(i).getString("bookrecno"));
                            book.setIsbn(jsonArray.getJSONObject(i).getString("isbn"));
                            list.add(book);
                        }

                        search.setTotal(jsonObject.getInt("total"));
                        search.setbList(list);
                        listener.onSuccess(search);


                    } catch (JSONException e) {
                        listener.onFailure(TAG, "连接异常");
                    }

                } else {
                    listener.onFailure(TAG, "连接失败");
                }
            }
        });
        asyncTaskAction.execute();
    }


    @Override
    public void getBookDetails(final Book book, final ActionListener<Book> listener) {
        AsyncTaskAction asyncTaskAction = new AsyncTaskAction(new AsyncTaskListener<String>() {
            @Override
            public String background() {
                return bookApi.getBookDetails(book.getRecNo());
            }

            @Override
            public void result(String result) {
                if (result != null) {

                    Log.d(TAG, result);
                    List<Holding> list = new ArrayList<>();
                    try {
                        JSONObject jsonObject = new JSONObject(result);
                        JSONArray jsonArray;
                        try {
                            jsonArray = jsonObject.getJSONArray("holdingList");
                        } catch (JSONException e) {
                            jsonArray = new JSONArray();
                        }

                        int totalLoan = 0;
                        int canLoan = 0;
                        for (int x = 0; x < jsonArray.length(); x++) {
                            Holding holding = new Holding();
                            try {
                                holding.setBarcode(jsonArray.getJSONObject(x).getString("barcode"));
                            } catch (JSONException e) {
                                holding.setBarcode("");
                            }
                            try {
                                holding.setLocal(jsonArray.getJSONObject(x).getString("curlocalname"));
                            } catch (JSONException e) {
                                holding.setLocal("");
                            }
                            try {
                                holding.setShelf(jsonArray.getJSONObject(x).getString("shelfno"));
                            } catch (JSONException e) {
                                holding.setShelf("");
                            }
                            try {
                                int status = jsonArray.getJSONObject(x).getInt("state");
                                holding.setStatus(status);
                                if (status == 2) {
                                    canLoan++;
                                }
                            } catch (JSONException e) {
                                holding.setStatus(0);
                            }
                            try {
                                int num = jsonArray.getJSONObject(x).getInt("totalloannum");
                                holding.setTotalLoanNum(num);
                                totalLoan += num;
                                book.setLoanNum(totalLoan);
                            } catch (JSONException e) {
                                holding.setTotalLoanNum(0);
                            }
                            list.add(holding);
                        }
                        book.setShelfNum(list.size());
                        book.setCanLoanNum(canLoan + 1);
                        book.sethList(list);

                        jsonObject = jsonObject.getJSONObject("biblios");
                        try {
                            book.setPage(jsonObject.getString("page"));
                        } catch (JSONException e) {
                            book.setPage("未知");
                        }
                        try {
                            book.setPrice(jsonObject.getString("price"));
                        } catch (JSONException e) {
                            book.setPrice("未知");
                        }
                        try {
                            book.setSize(jsonObject.getString("booksize"));
                        } catch (JSONException e) {
                            book.setSize("未知");
                        }
                        try {
                            book.setSummary(jsonObject.getString("summary"));
                        } catch (JSONException e) {
                            book.setSummary("暂无");
                        }


                        listener.onSuccess(book);


                    } catch (JSONException e) {
                        listener.onFailure(TAG, "连接异常");
                    }

                } else {
                    listener.onFailure(TAG, "连接失败");
                }
            }
        });
        asyncTaskAction.execute();
    }


    @Override
    public void getBookCover(final String isbn, final ActionListener<Map<String, String>> listener) {
        AsyncTaskAction asyncTaskAction = new AsyncTaskAction(new AsyncTaskListener<String>() {
            @Override
            public String background() {
                return bookApi.getBookCover(isbn);
            }

            @Override
            public void result(String result) {
                if (result != null) {
                    Log.d(TAG, result);
                    try {
                        JSONObject jsonObject = new JSONObject(result.substring(result.indexOf("(") + 1, result.lastIndexOf(")")));
                        JSONArray jsonArray = jsonObject.getJSONArray("result");
                        Map<String, String> map = new HashMap<>();

                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObj = jsonArray.getJSONObject(i);
                            map.put(jsonObj.get("isbn").toString(), jsonObj.get("coverlink").toString());
                        }
                        listener.onSuccess(map);


                    } catch (JSONException e) {
                        listener.onFailure(TAG, "连接异常");
                    }

                } else {
                    listener.onFailure(TAG, "连接失败");
                }
            }
        });
        asyncTaskAction.execute();
    }


    @Override
    public void consultant(final String msg, final ActionListener<Consult> listener) {
        AsyncTaskAction asyncTaskAction = new AsyncTaskAction(new AsyncTaskListener<String>() {
            @Override
            public String background() {
                return bookApi.consultant(msg);
            }

            @Override
            public void result(String result) {
                Log.d(TAG, result);
                if (result != null) {
                    try {
                        JSONObject jsonObject = new JSONObject(result);
                        Consult consult = new Consult();
                        consult.setClient(false);
                        consult.setTime(TimeUtil.getAllTime());
                        String resp = "";
                        try {
                            resp = jsonObject.getJSONArray("list").getJSONObject(0).getString("infoContent");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        consult.setText(resp + jsonObject.getString("robotResponse"));
                        listener.onSuccess(consult);


                    } catch (JSONException e) {
                        listener.onFailure(TAG, "连接异常");
                    }

                } else {
                    listener.onFailure(TAG, "连接失败");
                }
            }
        });
        asyncTaskAction.execute();
    }
}
